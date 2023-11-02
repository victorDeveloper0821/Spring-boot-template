package idv.victor.sideproject.system.security.service;

import idv.victor.sideproject.system.domain.MemberInfo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Spring Security user 資訊
 **/
@Data
public class CustomUserDetails implements UserDetails {
    /**
     * User 統一資訊
     */
    private MemberInfo memberInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return memberInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return memberInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return memberInfo.isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return memberInfo.isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return memberInfo.isPasswordLocked();
    }

    @Override
    public boolean isEnabled() {
        return !memberInfo.isAccountExpired() && !memberInfo.isAccountLocked();
    }
}
