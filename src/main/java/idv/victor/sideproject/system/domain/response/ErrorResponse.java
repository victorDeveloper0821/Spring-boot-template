package idv.victor.sideproject.system.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 錯誤訊息回傳格式
 */
@Data
public class ErrorResponse {
    /**
     * 狀態碼
     */
    private final String status;

    /**
     * 錯誤code
     */
    private final String errCode;

    /**
     * 錯誤訊息
     */
    private final String errMsg;

    /**
     * for
     * 欄位檢核錯誤詳細訊息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errors;

    /**
     * 建構子
     *
     * @param status   狀態碼
     * @param errCode  錯誤code
     * @param errorMsg 錯誤訊息
     */
    public ErrorResponse(String status, String errCode, String errorMsg) {
        this.status = status;
        this.errCode = errCode;
        this.errMsg = errorMsg;
    }

    /**
     * 建構子 for 欄位檢核錯誤
     *
     * @param status  狀態碼
     * @param errCode 錯誤code
     * @param errMsg  錯誤訊息
     * @param errors  欄位檢核錯誤詳細訊息
     */
    public ErrorResponse(String status, String errCode, String errMsg, Object errors) {
        this(status, errCode, errMsg);
        this.errors = errors;
    }
}
