package idv.victor.sideproject.system.security.service;

import idv.victor.sideproject.enums.ReturnCodes;
import idv.victor.sideproject.member.service.UserService;
import idv.victor.sideproject.system.domain.MemberInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService - Spring Security 取得 user 資訊
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * MemberService 會員服務
     */
    @Autowired
    private UserService memberService;

    /**
     * 登入時查找使用者資訊
     *
     * @param username the username identifying the user whose data is required.
     * @return 使用者資訊
     * @throws UsernameNotFoundException 找不到 User 資訊
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        CustomUserDetails details = new CustomUserDetails();
        MemberInfo memberInfo = memberService.findMemberByUserName(username);
        // 如果 memberInfo 為空，則拋出錯誤
        if (ObjectUtils.isEmpty(memberInfo)) {
            throw new UsernameNotFoundException(ReturnCodes.E0203.getStatusMsg());
        }
        details.setMemberInfo(memberInfo);
        return details;
    }
}
