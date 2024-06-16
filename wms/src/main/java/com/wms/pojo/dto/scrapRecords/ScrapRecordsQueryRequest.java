package com.wms.pojo.dto.scrapRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-09 20:05
 */
@Data
public class ScrapRecordsQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer scrapId;

    private Integer wareId;

    private Integer goodsId;

    private String userAccount;

    private Date startDate;

    private Date endDate;
}
