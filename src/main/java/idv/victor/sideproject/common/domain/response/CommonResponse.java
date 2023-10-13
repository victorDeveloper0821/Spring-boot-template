package idv.victor.sideproject.common.domain.response;

/**
 * 通用的 response 格式
 */
public class CommonResponse {
    /**
     * 錯誤代碼
     */
    private final String errCode;
    /**
     * 錯誤訊息
     */
    private final String errMsg;
    /**
     * 狀態碼
     */
    private String status;
    /**
     * 回應資料
     */
    private Object data;

    /**
     * Constructor
     *
     * @param errCode 錯誤代碼
     * @param errMsg  錯誤訊息
     */
    public CommonResponse(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;

    }
}
