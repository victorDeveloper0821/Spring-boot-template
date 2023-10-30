package idv.victor.sideproject.system.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @Author: Victor Tsai
 * @Date: 2023/10/26 - 下午 02:16
 * @Description: user相關資訊封裝
 **/
@Data
@Builder
public class MemberInfo {
    /**
     * 帳號
     */
    private String username;
    /**
     * 密碼
     */
    private String password;
    /**
     * 手機
     */
    private String cellphone;
    /**
     * 信箱
     */
    private String email;
    /**
     * 帳戶過期
     */
    private boolean isAccountExpired;
    /**
     * 帳戶鎖定
     */
    private boolean isAccountLocked;
    /**
     * 密碼過期
     */
    private boolean isPasswordLocked;
}
