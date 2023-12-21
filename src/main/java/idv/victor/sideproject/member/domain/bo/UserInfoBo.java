package idv.victor.sideproject.member.domain.bo;

import lombok.Data;

/**
 * 網站顯示會員資訊
 */
@Data
public class UserInfoBo {
    /**
     * 使用者名稱
     */
    private String userName;

    /**
     * 使用者姓名
     */
    private String name;

    /**
     * Email
     */
    private String email;

    /**
     * 手機
     */
    private String cellphone;
}
