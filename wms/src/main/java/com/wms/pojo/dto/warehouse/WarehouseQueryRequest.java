package com.wms.pojo.dto.warehouse;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-08 15:52
 */
@Data
public class WarehouseQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wareId;

    private String wareHead;

    private String wareName;

    private BigDecimal wareValue;

    private String wareNotes;

    private String wareAddress;
}
