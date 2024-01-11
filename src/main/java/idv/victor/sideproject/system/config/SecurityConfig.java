package idv.victor.sideproject.system.config;

import idv.victor.sideproject.enums.AppConfig;
import idv.victor.sideproject.system.security.filter.JwtAuthFilter;
import idv.victor.sideproject.system.security.filter.UserLoginFilter;
import idv.victor.sideproject.system.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * Spring Security 相關設定
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * URL White List
     */
    @Value("${path.whiteList}")
    private String[] whiteList;

    /**
     * JWT 認證用的 filter
     */
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    /**
     * UserDetailService
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;
    private AuthenticationManager authManager;

    /**
     * AuthenticationProvider - With Authentication from databases
     *
     * @return DaoAuthenticationProvider
     */
    @Bean
    @ConditionalOnProperty(prefix = "template", name = "auth", havingValue = "datasource")
    public DaoAuthenticationProvider authenticationDataSourceProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * AuthenticationProvider - InMemoryUserAuthentication
     *
     * @return DaoAuthenticationProvider
     */
    @Bean
    @ConditionalOnProperty(prefix = "template", name = "auth", havingValue = "InMemory", matchIfMissing = true)
    public DaoAuthenticationProvider authenticationInMemoryProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(InMemoryUserDetails());
        //authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * InMemory UserDetails here
     *
     * @return UserDetailsService
     */
    @Bean
    @ConditionalOnProperty(prefix = "template", name = "auth", havingValue = "InMemory", matchIfMissing = true)
    public UserDetailsService InMemoryUserDetails() {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


    /**
     * 隨機生成密碼
     *
     * @return 隨機生成密碼
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 弱掃過濾 設定
     *
     * @param http 前端資料
     * @return SecurityFilterChain Filter chain
     * @throws Exception ERROR訊息
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   List<AuthenticationProvider> authenticationProviders)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(Customizer.withDefaults())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeHttpRequests) -> {
                    authorizeHttpRequests
                            .requestMatchers(whiteList)
                            .permitAll().anyRequest().permitAll();
                });

        //        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(userLoginFilter(authenticationProviders), JwtAuthFilter.class);
        return http.build();
    }

    /**
     * AuthenticationManager 設定
     *
     * @return AuthenticationManager
     * @throws Exception ERROR訊息
     */
    @Bean
    public AuthenticationManager authenticationManager(List<AuthenticationProvider> authenticationProviders) {
        return new ProviderManager(authenticationProviders);
    }

    /**
     * 定義 user 登入用 filter
     *
     * @param authenticationProviders providers
     * @return UserLoginFilter 使用者登入用 filter
     */
    @Bean
    public UserLoginFilter userLoginFilter(List<AuthenticationProvider> authenticationProviders) throws Exception {
        return new UserLoginFilter(AppConfig.ApiPrefix + "member/login",
                                   authenticationManager(authenticationProviders));

    }
}
