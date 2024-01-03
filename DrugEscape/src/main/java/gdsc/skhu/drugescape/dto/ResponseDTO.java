package gdsc.skhu.drugescape.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    @Schema(description = "id")
    private String id;

    @Schema(description = "name")
    private String name;

    @Schema(description = "point")
    private String point;
}