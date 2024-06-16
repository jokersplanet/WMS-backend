package com.wms.pojo.dto.scrapRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-09 20:05
 */
@Data
public class ScrapRecordsAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer wareId;

    private Integer goodsId;

    private Long count;

    private Date time;

    private BigDecimal price;

    private BigDecimal value;

    private String userAccount;

    private String notes;
}
