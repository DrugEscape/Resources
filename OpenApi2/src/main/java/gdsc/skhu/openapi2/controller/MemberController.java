package gdsc.skhu.openapi2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member API", description = "사용자 API")
@RestController
public class MemberController {
    @Operation(summary = "메인페이지", description = "Main Page")
    @GetMapping("/main")
    public String main() {
        return "메인페이지";
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

    @Operation(summary = "로그인", description = "Login")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/login")
    public String login() {
        return "로그인";
    }

    @Operation(summary = "로그아웃", description = "Logout")
    @PostMapping("/logout")
    public String logout() {
        return "로그아웃";
    }

    @Operation(summary = "리프레시", description = "Refresh")
    @PostMapping("/refresh")
    public String refresh() {
        return "리프레시";
    }

    @Operation(summary = "마이페이지(리포트)", description = "Mypage")
    @GetMapping("/mypage")
    public String mypage() {
        return "마이페이지(리포트)";
    }
}