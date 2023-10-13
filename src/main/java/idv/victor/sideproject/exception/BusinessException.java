package idv.victor.sideproject.exception;

import idv.victor.sideproject.enums.ReturnCodes;
import lombok.Getter;

/**
 * 業務邏輯相關錯誤訊息
 */
@Getter
public class BusinessException extends Exception {

    /**
     * 錯誤代碼
     */
    private final String errCode;

    /**
     * 錯誤訊息
     */
    private final String errMsg;

    /**
     * 建構子
     *
     * @param returnCodes 錯誤碼enum
     */
    public BusinessException(ReturnCodes returnCodes) {
        this.errCode = returnCodes.getStatusCode();
        this.errMsg = returnCodes.getStatusMsg();
    }
}
