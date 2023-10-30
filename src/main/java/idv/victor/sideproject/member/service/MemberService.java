package idv.victor.sideproject.member.service;

import idv.victor.sideproject.system.domain.MemberInfo;

/**
 * @version 1.0
 * @Author: Victor Tsai
 * @Date: 2023/10/30 - 下午 01:50
 * @Description: 描述
 **/
public interface MemberService {
    /**
     * 以 userName 尋找 member
     *
     * @param userName 使用者名稱
     * @return 會員資料
     */
    MemberInfo findMemberByUserName(String userName);
}
