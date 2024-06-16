package com.wms.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-13 16:30
 */
@Data
public class GoodsVO implements Serializable {

    private Integer goodsId;

    private String goodsName;

    private UnitVO unitVO;

    private TypeClassVO upperClassVO;

    private TypeClassVO lowerClassVO;
}
