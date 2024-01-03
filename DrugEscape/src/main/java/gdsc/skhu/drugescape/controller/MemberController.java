package gdsc.skhu.drugescape.controller;

import gdsc.skhu.drugescape.dto.TokenDTO;
import gdsc.skhu.drugescape.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

// 각각의 기능 추가
@Tag(name = "Member API", description = "사용자 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "메인페이지", description = "Main Page")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/main")
    public String main() {
        return "메인 페이지";
    }

    @Operation(summary = "로그인", description = "Login")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/login")
    public String login() {
        return "로그인";
    }

    @Operation(summary = "로그아웃", description = "Logout")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/logout")
    public String logout() {
        return "로그아웃";
    }

    @Operation(summary = "리프레시", description = "Refresh")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/refresh")
    public String Refresh() {
        return "리프레시";
    }

    @Operation(summary = "회원가입", description = "Signup")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/signup")
    public String signup() {
        return "회원가입";
    }

    @Operation(summary = "마이페이지(리포트)", description = "Mypage")
    @GetMapping("/mypage")
    public String mypage() {
        return "마이페이지(리포트)";
    }

    @Operation(summary = "콜백", description = "Callback")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/api/oauth2/callback/google")
    public TokenDTO googleCallback(@RequestParam(name = "code") String code) {
        String googleAccessToken = memberService.getGoogleAccessToken(code);
        return loginOrSignup(googleAccessToken);
    }
    public TokenDTO loginOrSignup(String googleAccessToken) {
        return memberService.loginOrSignUp(googleAccessToken);
    }
}