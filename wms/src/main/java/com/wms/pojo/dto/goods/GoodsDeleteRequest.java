package com.wms.pojo.dto.goods;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-09 11:26
 */
@Data
public class GoodsDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;
}
