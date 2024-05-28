package com.wms.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-05-27 23:48
 */
@Data
public class RemoveRequest implements Serializable {
    /**
     * uid
     */
    private Integer uid;

    private static final long serialVersionUID = 1L;
}
