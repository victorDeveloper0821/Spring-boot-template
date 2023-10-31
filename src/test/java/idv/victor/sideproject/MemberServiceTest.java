package idv.victor.sideproject;

import idv.victor.sideproject.member.domain.entity.Member;
import idv.victor.sideproject.member.repository.MemberReposiroty;
import idv.victor.sideproject.member.service.MemberService;
import idv.victor.sideproject.system.domain.MemberInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void testFindMemberByUserName() {
        String testUserName = "testUser";
        Member testMember = new Member();
        testMember.setUserName(testUserName);
        testMember.setAccountExpired(false);

        MemberReposiroty mockRepository = mock(MemberReposiroty.class);
        when(mockRepository.findByUserName(testUserName)).thenReturn(Optional.of(testMember));

        ReflectionTestUtils.setField(memberService, "reposiroty", mockRepository);

        MemberInfo result = memberService.findMemberByUserName(testUserName);

        assertEquals(testUserName, result.getUsername());
        assertFalse(result.isAccountExpired());

        verify(mockRepository, times(1)).findByUserName(testUserName);
    }
}
