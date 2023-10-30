package idv.victor.sideproject.member.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Member 資訊
 *
 * @version 1.0
 * @Author: Victor Tsai
 * @Date: 2023/10/30 - 下午 01:45
 * @Description: 描述
 **/
@Entity
@Data
public class Member {
    /**
     * 用戶流水號
     */
    @Id
    @Column(name = "UUID")
    private String uuid;

    /**
     * 帳號
     */
    @Column(name = "userName")
    private String username;
    /**
     * 密碼
     */
    @Column(name = "password")
    private String password;
    /**
     * 手機
     */
    @Column(name = "cellPhone")
    private String cellphone;
    /**
     * 信箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 帳戶過期
     */
    @Column(name = "isAccountExpired")
    private boolean isAccountExpired;
    /**
     * 帳戶鎖定
     */
    @Column(name = "isAccountLocked")
    private boolean isAccountLocked;
    /**
     * 密碼過期
     */
    @Column(name = "isPasswordLocked")
    private boolean isPasswordLocked;
}
