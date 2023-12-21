package idv.victor.sideproject.system.security.filter;

import idv.victor.sideproject.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JWT 認證相關 filter
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * JWT 工具
     */
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 用於拋出 exception 到 Error Handler
     */
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    /**
     * 定義特定 http request 的 url 不需要經過此 filter
     *
     * @param request current HTTP request
     * @return boolean 是否不用經過 filter
     * @throws ServletException request 錯誤
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        List<AntPathRequestMatcher> antPathRequestMatcherList = new ArrayList<>();
        // swagger's path
        antPathRequestMatcherList.add(new AntPathRequestMatcher("/swagger-ui.html"));
        antPathRequestMatcherList.add(new AntPathRequestMatcher("/v3/**"));
        antPathRequestMatcherList.add(new AntPathRequestMatcher("/swagger-ui/**"));
        antPathRequestMatcherList.add(new AntPathRequestMatcher("/swagger-resources/**"));
        antPathRequestMatcherList.add(new AntPathRequestMatcher("/webjars/**"));

        return antPathRequestMatcherList.stream()
                                        .anyMatch(antPathRequestMatcher -> antPathRequestMatcher.matches(request));
    }

    /**
     * 執行 filter 相關邏輯
     *
     * @param request     http 請求
     * @param response    http 回應
     * @param filterChain FilterChain
     * @throws ServletException 例外
     * @throws IOException      例外
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // TODO 新增部分 url 不需經過此 filter (optional)
            // 解析 Authorization 跟 JWT
            String authorization = request.getHeader("Authorization");
            String jwtString = StringUtils.hasText(authorization) ? authorization.replace("Bearer ", "") : null;
            if (!StringUtils.hasText(jwtString)) {
                throw new IllegalArgumentException("JSON web token 不存在");
            }
            // TODO 新增 jwt 解析相關邏輯 - 取得 Authorization，並儲存 Authorization
            Claims claims = jwtUtils.extractClaim(jwtString);


        } catch (Exception e) {
            // 拋出 exception
            resolver.resolveException(request, response, null, e);
        }
    }
}
