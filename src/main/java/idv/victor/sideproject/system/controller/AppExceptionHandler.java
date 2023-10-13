package idv.victor.sideproject.system.controller;

import idv.victor.sideproject.common.domain.response.CommonResponse;
import idv.victor.sideproject.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 新增 Error Handling 相關邏輯
 */
@RestControllerAdvice
public class AppExceptionHandler {

    /**
     * 拋出BusinessException的情況
     *
     * @param e 例外
     * @return response 回應
     */
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BusinessException.class)
    public CommonResponse catchBusinessException(BusinessException e) {
        return new CommonResponse(e.getErrCode(), e.getErrMsg());
    }
}
