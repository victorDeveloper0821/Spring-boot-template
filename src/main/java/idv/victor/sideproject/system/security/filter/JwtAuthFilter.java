package idv.victor.sideproject.system.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 認證相關 filter
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

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
        // TODO 新增部分 url 不需經過此 filter
        // TODO 新增 jwt 基本檢核邏輯 - 檢核過期 or not
        // TODO 新增 jwt 解析相關邏輯 - 取得 Authorization，並儲存 Authorization

    }
}
