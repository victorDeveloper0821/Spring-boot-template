package idv.victor.sideproject.member.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import idv.victor.sideproject.common.domain.response.CommonResponse;
import idv.victor.sideproject.enums.ReturnCodes;
import idv.victor.sideproject.member.domain.entity.Member;
import idv.victor.sideproject.member.repository.MemberReposiroty;
import idv.victor.sideproject.member.service.UserService;
import idv.victor.sideproject.system.domain.MemberInfo;
import idv.victor.sideproject.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
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
     * JWT 工具類
     */
    @Autowired
    private JwtUtils jwtUtils;

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
     * 當使用者(Member權限)登入失敗時會做的事情
     *
     * @param request  http 請求
     * @param response http 回應
     * @throws IOException 讀取請求錯誤
     */
    @Override
    public void onLoginFailed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Object Mapper 序列化 json
        ObjectMapper om = new ObjectMapper();
        CommonResponse commonResponse =
                new CommonResponse(ReturnCodes.AE0001.getStatusCode(), ReturnCodes.AE0001.getStatusMsg(), null);
        response.setContentType("application/json; charset=UTF-8");
        // 登入失敗需 return http 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        // response 給 json
        PrintWriter writer = response.getWriter();
        String json = om.writeValueAsString(commonResponse);
        writer.println(json);
    }

    /**
     * 當使用者(Member權限)登入成功時會做的事情
     *
     * @param response   http 回應
     * @param authResult Authorization結果
     * @throws IOException 讀取請求錯誤
     */
    @Override
    public void onLoginSuccessed(HttpServletResponse response, Authentication authResult) throws IOException {
        ObjectMapper om = new ObjectMapper();
        Map map = Map.of("token", jwtUtils.generateToken(authResult.getName(), "Member", 5));
        // CommonResponse 成功
        CommonResponse commonResponse =
                new CommonResponse(ReturnCodes.AE0000.getStatusCode(), ReturnCodes.AE0000.getStatusMsg(), map);
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = response.getWriter();
            String json = om.writeValueAsString(commonResponse);
            writer.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
