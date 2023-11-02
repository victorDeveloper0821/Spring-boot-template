package idv.victor.sideproject.member.service.impl;

import idv.victor.sideproject.member.domain.entity.Member;
import idv.victor.sideproject.member.repository.MemberReposiroty;
import idv.victor.sideproject.member.service.MemberService;
import idv.victor.sideproject.system.domain.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 找尋 member 資料用
 **/
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberReposiroty reposiroty;

    /**
     * 以 userName 尋找 member
     *
     * @param userName 使用者名稱
     * @return 會員資料
     */
    @Override
    public MemberInfo findMemberByUserName(String userName) {
        Optional<Member> option = reposiroty.findByUserName(userName);
        if (option.isEmpty()) throw new UsernameNotFoundException("找不到 user");
        Member member = option.get();
        return MemberInfo.builder().username(member.getUserName()).isAccountExpired(member.isAccountExpired()).build();
    }
}
