package gdsc.skhu.drugescape.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenDTO {
    @Schema(description = "액세스 토큰")
    private String accessToken;
}