package idv.victor.sideproject.member.domain.requst;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * User 首次註冊、更新資料
 */
@Data
public class RegisterReqDTO {
    /**
     * 使用者名稱
     */
    @NotBlank
    private String username;

    /**
     * 使用者密碼
     */
    @NotBlank
    private String password;

    /**
     * 使用者密碼確認
     */
    @NotBlank
    private String confirmPassword;

    /**
     * 電子信箱
     */
    @NotBlank
    private String email;

    /**
     * 手機號碼
     */
    private String cellphone;

    /**
     * 於平台上的名字
     */
    private String displayname;
}
