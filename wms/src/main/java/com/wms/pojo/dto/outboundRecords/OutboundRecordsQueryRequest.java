package com.wms.pojo.dto.outboundRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-09 17:27
 */
@Data
public class OutboundRecordsQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer outboundId;

    private String goodsName;

    private String time;

    private Integer count;

    private Integer wareId;

    private String userAccount;

    private BigDecimal price;

    private BigDecimal value;

    private String notes;
}
