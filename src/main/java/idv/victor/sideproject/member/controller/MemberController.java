package idv.victor.sideproject.member.controller;

import idv.victor.sideproject.common.domain.response.CommonResponse;
import idv.victor.sideproject.enums.AppConfig;
import idv.victor.sideproject.member.domain.requst.LoginReqDTO;
import idv.victor.sideproject.member.domain.requst.RegisterReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 **/
@Tag(name = "一般會員操作相關 API ")
@RestController
@RequestMapping(value = AppConfig.ApiPrefix + "member")
public class MemberController {
    /**
     * 使用者註冊接口
     *
     * @param registerReqDTO 會員註冊資料
     */
    @Operation(summary = "一般使用者註冊", description = "一般使用者註冊端點")
    @ApiResponses({
            @ApiResponse(responseCode = "401", description = "沒有權限"),
            @ApiResponse(responseCode = "404", description = "找不到路徑")
    })
    @ResponseBody
    @PostMapping(value = "/register")
    public CommonResponse userRegister(@RequestBody RegisterReqDTO registerReqDTO) {
        return new CommonResponse("A0001", "作業成功", null);
    }

    /**
     * 使用者登入接口
     */
    @Operation(summary = "一般使用者登入", description = "提供一般使用者登入的 API 端點")
    @ApiResponses({
            @ApiResponse(responseCode = "401", description = "登入認證失敗"),
            @ApiResponse(responseCode = "200", description = "使用者登入成功"),
            @ApiResponse(responseCode = "404", description = "找不到路徑")
    })
    @ResponseBody
    @PostMapping(value = "/login")
    public CommonResponse userLogin(@RequestBody LoginReqDTO loginReqDTO) {
        return new CommonResponse("A0001", "作業成功", null);
    }

    /**
     * 以 userIdEncrypted 更新使用者資訊
     *
     * @param userIdEncrypted 使用者ID加密
     * @param registerReqDTO  更新使用者資訊
     */
    @ResponseBody
    @PatchMapping(value = "/{userIdEncrypted}")
    public CommonResponse updateUserInfo(@PathVariable String userIdEncrypted,
                                         @RequestBody RegisterReqDTO registerReqDTO) {
        return new CommonResponse("A0001", "作業成功", null);
    }

    /**
     * 查詢 user 自身資訊
     *
     * @param userIdEncrypted 使用者ID加密
     */
    @ResponseBody
    @GetMapping(value = "/{userIdEncrypted}")
    public CommonResponse showUserInfo(@PathVariable String userIdEncrypted) {
        return new CommonResponse("A0001", "作業成功", null);
    }

    /**
     * user 刪除
     *
     * @param userIdEncrypted 使用者ID加密
     */
    @ResponseBody
    @DeleteMapping(value = "/{userIdEncrypted}")
    public CommonResponse deleteUser(@PathVariable String userIdEncrypted) {
        return new CommonResponse("A0001", "作業成功", null);
    }
}
