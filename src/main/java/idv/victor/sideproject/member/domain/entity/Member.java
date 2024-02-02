package idv.victor.sideproject.member.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;

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
    @Column(name = "username")
    private String userName;
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
    /**
     * 建立日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createdAt;

    /**
     * 過期時間
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate updatedAt;

    /**
     * 密碼檔
     */
    @OneToMany(mappedBy = "member")
    private HashSet<PasswordProfile> passwordProfiles = new HashSet<>();
}
