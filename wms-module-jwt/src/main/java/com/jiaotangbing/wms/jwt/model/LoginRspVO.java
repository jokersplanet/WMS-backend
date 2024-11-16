package com.jiaotangbing.wms.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lwh
 * @create 2024-11-13 11:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRspVO {

    /**
     * Token 值
     */
    private String token;

}

