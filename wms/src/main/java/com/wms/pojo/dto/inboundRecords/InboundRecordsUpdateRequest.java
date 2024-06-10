package com.wms.pojo.dto.inboundRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-09 17:06
 */
@Data
public class InboundRecordsUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer inboundId;

    private String time;

    private Long count;

    private String goodsName;

    private Integer wareId;

    private String userAccount;

    private BigDecimal price;

    private BigDecimal value;

    private String notes;
}
