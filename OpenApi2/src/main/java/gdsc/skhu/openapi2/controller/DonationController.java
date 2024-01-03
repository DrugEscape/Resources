package gdsc.skhu.openapi2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@Tag(name = "Donation API", description = "기부 API")
@RestController
public class DonationController {
    @Operation(summary = "기부페이지", description = "Donate Page")
    @GetMapping("/donate")
    public String donate() {
        return "기부 조회";
    }

    @Operation(summary = "Donated 버튼 클릭", description = "Donated Button")
    @PostMapping("/donate")
    public String donate(Point point) {
        return "redirect:clear"; // 기부 완료 페이지로 넘어가기
    }

    @Operation(summary = "기부 완료", description = "Donate Clear")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/donate/clear")
    public String clear() {
        return "기부 완료";
    }
}