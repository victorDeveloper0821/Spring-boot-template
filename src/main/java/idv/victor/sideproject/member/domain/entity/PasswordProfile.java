package idv.victor.sideproject.member.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * 密碼檔
 */
@Entity
public class PasswordProfile {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 加密的密碼
     */
    private String password;

    /**
     * 狀態
     */
    private String status;

    /**
     * 建立日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createdAt;

    /**
     * 過期時間
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate expiredAt;

    /**
     * 會員資料 many to one
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UUID")
    private Member member;
}
