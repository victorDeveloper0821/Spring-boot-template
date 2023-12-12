package idv.victor.sideproject.member.controller;

import idv.victor.sideproject.enums.AppConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

/**
 * @version 1.0
 * @Author: Victor Tsai
 * @Date: 2023/10/30 - 下午 01:43
 * @Description: 會員登入相關 controller
 **/
@Tag(name = "會員相關 API ")
@RestController
@RequestMapping(value = AppConfig.ApiPrefix + "member")
public class MemberController {
    /**
     * 使用者註冊接口
     *
     * @param productCode 產品代碼
     */
    @Operation(summary = "標題", description = "敘述")
    @ApiResponses({
            @ApiResponse(responseCode = "401", description = "沒有權限"),
            @ApiResponse(responseCode = "404", description = "找不到路徑")
    })
    @ResponseBody
    @GetMapping(value = "/register")
    public void userRegister(@PathVariable String productCode) {

    }

    /**
     * 使用者登入接口
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public void userLogin(HashMap<String, Objects> product) {

    }

    /**
     * 以 userIdEncrypted 更新使用者資訊
     *
     * @param userIdEncrypted 使用者ID加密
     */
    @ResponseBody
    @PatchMapping(value = "/{userIdEncrypted}")
    public void UpdateUserInfo(@PathVariable String userIdEncrypted) {

    }

    /**
     * 查詢 user 自身資訊
     *
     * @param userIdEncrypted 使用者ID加密
     */
    @ResponseBody
    @GetMapping(value = "/{userIdEncrypted}")
    public void showUserInfo(@PathVariable String userIdEncrypted) {

    }
}
