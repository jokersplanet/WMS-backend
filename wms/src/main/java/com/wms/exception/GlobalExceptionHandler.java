package com.wms.exception;

import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lwh
 * @create 2024-05-28 17:13
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Response<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultsSet.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Response<?> runtimeExceptionHandler(RuntimeException e){
        log.error("RuntimeException",e);
        return ResultsSet.error(ErrorCode.SYSTEM_ERROR,"系统错误");
    }
}
