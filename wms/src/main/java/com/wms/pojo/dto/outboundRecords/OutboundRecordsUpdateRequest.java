package com.wms.pojo.dto.outboundRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-09 19:53
 */
@Data
public class OutboundRecordsUpdateRequest implements Serializable {

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
