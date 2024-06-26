package com.wms.utils;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.common.Response;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @author lwh
 * @create 2024-06-25 19:59
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response, Response r){
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.HTTP_OK);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(),r);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
