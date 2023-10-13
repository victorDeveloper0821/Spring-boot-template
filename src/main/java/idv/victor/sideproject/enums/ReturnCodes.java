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
     * A0001 執行成功
     */
    Success("Success", "執行成功");

    private String statusCode;

    private String statusMsg;
}
