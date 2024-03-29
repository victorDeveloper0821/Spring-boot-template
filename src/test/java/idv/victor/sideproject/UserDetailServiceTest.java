package idv.victor.sideproject;

import idv.victor.sideproject.member.service.UserService;
import idv.victor.sideproject.system.security.service.CustomUserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 測試 Spring Security UserDetails
 */
@SpringBootTest
public class UserDetailServiceTest {

    /**
     * UserDetailsService
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Mocked memberService
     */
    @MockBean
    private UserService memberService;

    /**
     * 測試 Member 不存在時，是否拋出 exception
     */
    @Test
    @DisplayName("測試 User 不存在的情況")
    public void testUserNotFound() {
        String userId = "testiing";
        // test userid with null results
        Mockito.when(memberService.findMemberByUserName(userId)).thenReturn(null);

        // 使用 assertThrows 來確認指定的異常是否被拋出
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(userId);
        });
    }
}
