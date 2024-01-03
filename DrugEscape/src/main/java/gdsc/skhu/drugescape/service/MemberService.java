package gdsc.skhu.drugescape.service;

import com.google.gson.Gson;
import gdsc.skhu.drugescape.domain.model.Member;
import gdsc.skhu.drugescape.domain.model.Role;
import gdsc.skhu.drugescape.domain.repository.MemberRepository;
import gdsc.skhu.drugescape.dto.MemberDTO;
import gdsc.skhu.drugescape.dto.TokenDTO;
import gdsc.skhu.drugescape.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

// 로그인과 회원가입을 분리 + 로그아웃, 리프레시 기능을 추가
@Service
public class MemberService {
    private final String GOOGLE_TOKEN_URL;
    private final String GOOGLE_CLIENT_ID;
    private final String GOOGLE_CLIENT_SECRET;
    private final String GOOGLE_REDIRECT_URI;

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public MemberService(@Value("${GOOGLE_TOKEN_URL}") String googleTokenUrl,
                         @Value("${GOOGLE_CLIENT_ID}") String googleClientId,
                         @Value("${GOOGLE_CLIENT_SECRET}") String googleClientSecret,
                         @Value("${GOOGLE_REDIRECT_URI}") String googleRedirectUri,
                         MemberRepository memberRepository, TokenProvider tokenProvider) {
        this.GOOGLE_TOKEN_URL = googleTokenUrl;
        this.GOOGLE_CLIENT_ID = googleClientId;
        this.GOOGLE_CLIENT_SECRET = googleClientSecret;
        this.GOOGLE_REDIRECT_URI = googleRedirectUri;
        this.memberRepository = memberRepository;
        this.tokenProvider = tokenProvider;
    }

    public String getGoogleAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = Map.of(
                "code", code,
                "scope", "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email",
                "client_id", GOOGLE_CLIENT_ID,
                "client_secret", GOOGLE_CLIENT_SECRET,
                "redirect_uri", GOOGLE_REDIRECT_URI,
                "grant_type", "authorization_code"
        );

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GOOGLE_TOKEN_URL, params, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, TokenDTO.class)
                    .getAccessToken();
        }
        throw new RuntimeException("구글 엑세스 토큰을 가져오는데 실패했습니다.");
    }

    public TokenDTO loginOrSignUp(String googleAccessToken) {
        MemberDTO memberDTO = getAccountDTO(googleAccessToken);
        if (!memberDTO.getVerifiedEmail()) {
            throw new RuntimeException("이메일 인증이 되지 않은 유저입니다.");
        }
        Member member = memberRepository.findByEmail(memberDTO.getEmail()).orElseGet(() ->
                memberRepository.save(Member.builder()
                        .name(memberDTO.getName())
                        .email(memberDTO.getEmail())
                        .password(memberDTO.getPassword())
                        .point(memberDTO.getPoint())
                        .role(Role.USER)
                        .build())
        );
        return tokenProvider.createToken(member);
    }

    public MemberDTO getAccountDTO(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        // url 반드시 수정
        String url = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String json = responseEntity.getBody();
            Gson gson = new Gson();
            return gson.fromJson(json, MemberDTO.class);
        }
        throw new RuntimeException("유저 정보를 가져오는데 실패했습니다.");
    }
}