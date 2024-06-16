package com.wms.pojo.dto.outboundRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-09 17:27
 */
@Data
public class OutboundRecordsQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer outboundId;

    private Integer goodsId;

    private Integer wareId;

    private String userAccount;

    private Date startDate;

    private Date endDate;
}
