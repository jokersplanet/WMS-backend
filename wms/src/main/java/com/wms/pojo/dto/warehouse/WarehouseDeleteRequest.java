package com.wms.pojo.dto.warehouse;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 16:15
 */
@Data
public class WarehouseDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wareId;
}
