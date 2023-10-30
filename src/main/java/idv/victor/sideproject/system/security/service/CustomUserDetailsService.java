package idv.victor.sideproject.system.security.service;

import idv.victor.sideproject.member.service.MemberService;
import idv.victor.sideproject.system.domain.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * MemberService 會員服務
     */
    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        CustomUserDetails details = new CustomUserDetails();
        MemberInfo memberInfo = memberService.findMemberByUserName(username);
        details.setMemberInfo(memberInfo);
        return details;
    }
}
