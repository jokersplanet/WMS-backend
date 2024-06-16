package com.wms.pojo.dto.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-08 23:28
 */
@Data
public class GoodsQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer goodsId;

    private String goodsName;

    private Integer unitId;

    private Integer wareId;

    private Integer upperClassId;

    private Integer lowerClassId;

    private Date startDate;

    private Date endDate;
}
