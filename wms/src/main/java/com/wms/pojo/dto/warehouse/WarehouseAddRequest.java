package com.wms.pojo.dto.warehouse;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-08 16:01
 */
@Data
public class WarehouseAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String wareHead;

    private String wareName;

    private BigDecimal wareValue;

    private String wareNotes;

    private String wareAddress;
}
