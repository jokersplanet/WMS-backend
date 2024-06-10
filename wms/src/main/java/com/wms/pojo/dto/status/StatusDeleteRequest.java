package com.wms.pojo.dto.status;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-07 22:42
 */
@Data
public class StatusDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
}
