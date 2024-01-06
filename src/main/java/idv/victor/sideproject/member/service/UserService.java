package idv.victor.sideproject.member.service;

import idv.victor.sideproject.system.domain.MemberInfo;

/**
 * 使用者相關服務
 **/
public interface UserService {
    /**
     * 以 userName 尋找 member
     *
     * @param userName 使用者名稱
     * @return 會員資料
     */
    MemberInfo findMemberByUserName(String userName);

    /**
     * 當使用者登入成功時會做的事情
     */
    void onLoginFailed();

    /**
     * 當使用者登入失敗時會做的事情
     */
    void onLoginSuccessed();
}
