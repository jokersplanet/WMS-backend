package com.wms.exception;

import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
        return Response.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Response<?> runtimeExceptionHandler(RuntimeException e){
        log.error("RuntimeException",e);
        return Response.error(ErrorCode.SYSTEM_ERROR,e.getMessage());
    }

    /**
     * spring security异常
     * @param e
     * @return
     * @throws AccessDeniedException
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Response<?> authorityException(AccessDeniedException e) throws AccessDeniedException{
        return Response.error(ErrorCode.PERMISSION);
    }
}
