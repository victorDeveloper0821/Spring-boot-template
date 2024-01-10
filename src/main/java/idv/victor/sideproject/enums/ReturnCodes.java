package idv.victor.sideproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * API 狀態代碼Enum
 */
@Getter
@AllArgsConstructor
public enum ReturnCodes {

    /**
     * Success 執行成功
     */
    Success("Success", "執行成功"),
    /**
     * Success 執行成功
     */
    AE0000("AE0000", "使用者登入成功"),
    /**
     * Success 執行成功
     */
    AE0001("AE0001", "使用者登入失敗"),

    /**
     * E0400 不正確請求
     */
    E0400("E0400", "不正確請求"),
    /**
     * E0401 未被認證的請求
     */
    E0401("E0401", "未被認證的請求"),
    /**
     * E0403 權限不足
     */
    E0403("E0403", "權限不足"),
    /**
     * E0404 找不到資源
     */
    E0404("E0404", "找不到資源"),
    /**
     * E0405 不支援此方法
     */
    E0405("E0405", "不支援此方法"),
    /**
     * E05XX 系統內部錯誤
     */
    E05XX("E05XX", "系統內部錯誤"),

    /**
     * E0203 查無使用者
     */
    E0203("E0203", "查無使用者");

    private String statusCode;

    private String statusMsg;
}
