package idv.victor.sideproject.member.domain.response;

import idv.victor.sideproject.member.domain.bo.UserInfoBo;
import lombok.Data;

/**
 * 會員登入成功物件
 */
@Data
public class LoginRespDTO {
    /**
     * 使用者基本資訊
     */
    private UserInfoBo userInfoBo;


}
