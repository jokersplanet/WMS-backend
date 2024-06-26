package com.wms.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private int code;//编码 200/400
    private String msg;//成功/失败
    private T data;

    //构造私有化
    private Response() {
    }
    private Response(ErrorCode errorCode){
    }
    private Response(int code,String message){
    }
    private Response(ErrorCode errorCode,String msg){}
    public static <T> Response<T> build(T data,ErrorCode errorCode){
        Response<T> response = new Response<>();
        if(data != null){
            response.setData(data);
        }
        response.setCode(errorCode.getCode());
        response.setMsg(errorCode.getMessage());
        return response;
    }

    //无数据成功
    public static <T> Response<T> success(){
        return build(null,ErrorCode.SUCCESS);
    }
    //有数据成功
    public static <T> Response<T> success(T data){
        return build(data,ErrorCode.SUCCESS);
    }

    //无数据失败
    public static <T> Response<T> fail(){
        return build(null,ErrorCode.FAIL);
    }
    //有数据失败
    public static <T> Response<T> fail(T data){
        return build(data,ErrorCode.FAIL);
    }

    public Response<T> message(String msg){
        this.setMsg(msg);
        return this;
    }
    public Response<T> code(Integer code){
        this.setCode(code);
        return this;
    }
    public static Response error(ErrorCode errorCode){
        return new Response(errorCode);
    }
    public static Response error(int code,String message){
        return new Response(code,message);
    }
    public static Response error(ErrorCode errorCode,String message){
        return new Response(errorCode.getCode(),message);
    }
}
