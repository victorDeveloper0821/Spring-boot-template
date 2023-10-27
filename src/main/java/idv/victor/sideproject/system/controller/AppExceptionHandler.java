package idv.victor.sideproject.system.controller;

import idv.victor.sideproject.enums.ReturnCodes;
import idv.victor.sideproject.exception.BusinessException;
import idv.victor.sideproject.system.domain.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;

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
    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = BusinessException.class)
    public ErrorResponse catchBusinessException(BusinessException e) {
        return new ErrorResponse(HttpStatus.OK.getReasonPhrase(), e.getErrCode(), e.getErrMsg());
    }

    /**
     * 處理 400
     *
     * @param e MethodArgumentNotValidException
     * @return response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentNotValidExceptionType(MethodArgumentNotValidException e) {
        // 取得錯誤欄位&錯誤訊息
        Map<String, List<String>> errorMap = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();
            if (errorMap.containsKey(fieldName)) {
                errorMap.get(fieldName).add(fieldError.getDefaultMessage());
            } else {
                List<String> tempList = new ArrayList<>();
                tempList.add(fieldError.getDefaultMessage());
                errorMap.put(fieldName, tempList);
            }
        }

        // 重新組裝錯誤訊息
        LinkedHashMap<String, Object> errorMsgMap = new LinkedHashMap<>();
        errorMap.forEach((key, value) -> errorMsgMap.put(key, String.join(",", value)));

        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ReturnCodes.E0400.getStatusCode(),
                                 ReturnCodes.E0400.getStatusMsg(), errorMsgMap);
    }

    /**
     * 處理 400 其他錯誤
     *
     * @param e HttpMessageNotReadableException
     * @return response
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse httpMessageNotReadableExceptionType(HttpMessageNotReadableException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ReturnCodes.E0400.getStatusCode(),
                                 ReturnCodes.E0400.getStatusMsg());
    }

    /**
     * 處理404
     *
     * @param e NoHandlerFoundException
     * @return response
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse noHandlerFoundExceptionType(NoHandlerFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), ReturnCodes.E0404.getStatusCode(),
                                 ReturnCodes.E0404.getStatusMsg());
    }

    /**
     * 處理405
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return response
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse httpRequestMethodNotSupportedExceptionType(HttpRequestMethodNotSupportedException e) {
        return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), ReturnCodes.E0405.getStatusCode(),
                                 ReturnCodes.E0405.getStatusMsg());
    }

    /**
     * 處理401
     *
     * @param e ExpiredJwtException
     * @return response
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse httpUnAuthorizedException(ExpiredJwtException e) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.getReasonPhrase(), ReturnCodes.E0401.getStatusCode(),
                                 ReturnCodes.E0401.getStatusMsg());
    }
}
