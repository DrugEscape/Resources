package gdsc.skhu.openapi2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Map API", description = "지도 API")
@RestController
public class MapController {
    @Operation(summary = "지도페이지", description = "Map Page")
    @GetMapping("/map")
    public String map() {
        return "지도 조회";
    }

    @Operation(summary = "상담센터 아이콘 클릭", description = "Counsel ICON")
    @GetMapping("/map/counsel")
    public String counsel() {
        return "상담센터";
    }

    @Operation(summary = "치료센터 아이콘 클릭", description = "Treat ICON")
    @GetMapping("/map/treat")
    public String treat() {
        return "치료센터";
    }

    @Operation(summary = "상담센터 상세정보", description = "Counsel Detail")
    @GetMapping("/map/counsel/detail")
    public String CounselDetail() {
        return "상담센터 상세정보";
    }

    @Operation(summary = "치료센터 상세정보", description = "Treat Detail")
    @GetMapping("/map/treat/detail")
    public String TreatDetail() {
        return "치료센터 상세정보";
    }
}