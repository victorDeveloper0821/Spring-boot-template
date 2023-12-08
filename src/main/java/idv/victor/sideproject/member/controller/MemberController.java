package idv.victor.sideproject.member.controller;

import idv.victor.sideproject.enums.AppConfig;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

/**
 * @version 1.0
 * @Author: Victor Tsai
 * @Date: 2023/10/30 - 下午 01:43
 * @Description: 會員登入相關 controller
 **/
@RestController
@RequestMapping(value = AppConfig.ApiPrefix + "member")
public class MemberController {
    /**
     * 使用者註冊接口
     *
     * @param productCode 產品代碼
     */
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
