package idv.victor.sideproject.member.service.impl;

import idv.victor.sideproject.member.domain.entity.Member;
import idv.victor.sideproject.member.repository.MemberReposiroty;
import idv.victor.sideproject.member.service.UserService;
import idv.victor.sideproject.system.domain.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 找尋 member 資料用
 */
@Service
public class MemberServiceImpl implements UserService {
    /**
     * Member 資料表 repository (DAO)
     */
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
        if (option.isEmpty()) {
            return null;
        }
        Member member = option.get();
        return MemberInfo.builder().username(member.getUserName()).isAccountExpired(member.isAccountExpired()).build();
    }

    /**
     * 當使用者(Member權限)登入成功時會做的事情
     */
    @Override
    public void onLoginFailed() {

    }

    /**
     * 當使用者(Member權限)登入失敗時會做的事情
     */
    @Override
    public void onLoginSuccessed() {

    }
}
