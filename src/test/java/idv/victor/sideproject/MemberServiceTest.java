package idv.victor.sideproject;

import idv.victor.sideproject.member.domain.entity.Member;
import idv.victor.sideproject.member.repository.MemberReposiroty;
import idv.victor.sideproject.member.service.UserService;
import idv.victor.sideproject.system.domain.MemberInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the MemberService class.
 */
@SpringBootTest
public class MemberServiceTest {

    /**
     * User查詢相關 service
     */
    @Autowired
    private UserService memberService;

    /**
     * Mocked Repository
     */
    @MockBean
    private MemberReposiroty mockRepository;

    /**
     * Test case for finding a member by username.
     */
    @Test
    @DisplayName("查詢 Member 功能")
    void testFindMemberByUserName() {
        // Prepare mock data
        String testUserName = "testUser";
        Member testMember = new Member();
        testMember.setUserName(testUserName);
        testMember.setAccountExpired(false);

        // Mock a method return beans (可以是 Service 或是 Repository)
        when(mockRepository.findByUserName(testUserName)).thenReturn(Optional.of(testMember));

        // Call the method to be tested
        MemberInfo result = memberService.findMemberByUserName(testUserName);

        // Assert the result
        assertEquals(testUserName, result.getUsername());
        assertFalse(result.isAccountExpired());

        // Verify that the repository method was called once with the expected argument
        verify(mockRepository, times(1)).findByUserName(testUserName);
    }
}
