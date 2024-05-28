package com.wms.exception;

import com.wms.common.ErrorCode;

/**
 * @author lwh
 * @create 2024-05-26 22:41
 */
public class BusinessException extends RuntimeException{
    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code,String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
    public BusinessException(ErrorCode errorCode, String message){
        super(message);
        this.code = errorCode.getCode();
    }
    public int getCode(){
        return code;
    }
}
