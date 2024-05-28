package com.wms.common;

/**
 * @author lwh
 * @create 2024-05-26 10:57
 */
public enum ErrorCode {
    SUCCESS(200,"成功"),
    REQUEST_ERROR(400,"请求错误"),
    NOT_LOGIN_ERROR(401,"未登录"),
    SYSTEM_ERROR(500,"系统错误");



    /**
     * 状态码
     */
    private final int code;
    /**
     * 返回信息
     */
    private final String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
