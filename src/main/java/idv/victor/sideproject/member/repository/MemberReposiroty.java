package idv.victor.sideproject.member.repository;

import idv.victor.sideproject.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 查詢　member
 *
 * @version 1.0
 * @Author: Victor Tsai
 * @Date: 2023/10/30 - 下午 01:45
 * @Description: 描述
 **/
public interface MemberReposiroty extends JpaRepository<Member, String> {
    
    Optional<Member> findByUserName(String username);
}
