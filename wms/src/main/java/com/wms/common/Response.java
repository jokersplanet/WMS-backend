package com.wms.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private int code;//编码 200/400
    private String msg;//成功/失败
    private T data;

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Response(ErrorCode errorCode){
        this(errorCode.getCode(),errorCode.getMessage(),null);
    }
}
