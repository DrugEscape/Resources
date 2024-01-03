package gdsc.skhu.openapi2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@Tag(name = "Management API", description = "관리 API")
@RestController
public class ManagementController {
    @Operation(summary = "관리페이지", description = "Manage Page")
    @GetMapping("/manage")
    public String manage() {
        return "관리 조회";
    }

    @Operation(summary = "Submit 버튼 클릭", description = "Submit Button")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("/manage")
    public String manage(Point point) {
        return "redirect:save";
    }

    @Operation(summary = "정보 저장 완료(팝업)", description = "Ssve Page")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/manage/save")
    public String save() {
        return "정보 저장 완료";
    }
}