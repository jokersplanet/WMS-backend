package com.wms.model.common;

import lombok.Data;

/**
 * @author lwh
 * @create 2024-05-21 20:35
 */
@Data
public class Response<T> {
    private int code;//编码 200/400
    private String msg;//成功/失败
    private Long total;
    private T data;

    public Response response(int code, String msg, Long total, T data) {
        Response res = new Response();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }

    public Response fail() {
        return response(400, "失败", 0L, null);
    }

    public Response success(long total, T data) {
        return response(200, "成功", total, data);
    }
}
