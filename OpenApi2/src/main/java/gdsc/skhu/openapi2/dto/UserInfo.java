package gdsc.skhu.openapi2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// 테스트용, @Schema를 사용하여 API 문서화가 잘 되도록 도와줌
@Data
public class UserInfo {
    @Schema(description = "id", example = "1")
    private String id;
    @Schema(description = "name", example = "홍길동")
    private String name;
    @Schema(description = "email", example = "test@gmail.com")
    private String email;
    @Schema(description = "password", example = "000000")
    private String password;
}