package idv.victor.sideproject.member.domain.requst;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 會員登入 Request
 */
@Data
public class LoginReqDTO {
    /**
     * 登入類別
     */
    private String loginType;
    /**
     * 使用者名稱
     */
    @NotBlank
    private String username;
    /**
     * 密碼
     */
    @NotBlank
    private String password;

    /**
     * 驗證碼
     */
    @NotBlank
    private String captcha;
}
