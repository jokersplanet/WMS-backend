package com.wms.pojo.dto.unit;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 22:06
 */
@Data
public class UnitAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String unitName;
}
