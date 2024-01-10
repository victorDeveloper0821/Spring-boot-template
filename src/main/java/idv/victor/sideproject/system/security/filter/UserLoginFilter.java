package idv.victor.sideproject.system.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import idv.victor.sideproject.member.domain.requst.LoginReqDTO;
import idv.victor.sideproject.member.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

/**
 * Login 相關邏輯
 */
public class UserLoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * UserService (已會員為範例)
     */
    @Autowired
    private UserService userService;

    /**
     * User Login Filter
     *
     * @param defaultFilterProcessesUrl url matcher
     * @param authenticationManager     authenticationManager
     */
    public UserLoginFilter(String defaultFilterProcessesUrl,
                           AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authenticationManager);
    }

    /**
     * 取得 user login 帳號密碼
     *
     * @param request  from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     *                 redirect as part of a multi-stage authentication process (such as OIDC).
     * @return Authentication
     * @throws AuthenticationException 驗證錯誤
     * @throws IOException             IO 錯誤
     * @throws ServletException        Servlet 錯誤
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // todo check 是否有登入前置作業 (optional)

        // get json request body
        String jsonBody = IOUtils.toString(request.getReader());
        ObjectMapper om = new ObjectMapper();
        LoginReqDTO loginReqDTO = om.readValue(jsonBody, LoginReqDTO.class);
        // 由 authenticationManager 進行驗證
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(loginReqDTO.getUsername(), loginReqDTO.getPassword())
        );
    }

    /**
     * 處理 user 登入成功邏輯
     *
     * @param request    http 請求
     * @param response   http 回應
     * @param chain      filterChain
     * @param authResult the object returned from the <tt>attemptAuthentication</tt>
     *                   method.
     * @throws IOException      IOException
     * @throws ServletException Serverlet 錯誤
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        userService.onLoginSuccessed(request, response);
    }

    /**
     * 處理 user 登入失敗邏輯
     *
     * @param request  http 請求
     * @param response http 回應
     * @param failed   Faild 例外
     * @throws IOException      IOException
     * @throws ServletException Serverlet 錯誤
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        userService.onLoginFailed(request, response);
    }
}
