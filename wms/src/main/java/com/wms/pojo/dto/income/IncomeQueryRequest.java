package com.wms.pojo.dto.income;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-06 22:36
 */
@Data
public class IncomeQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer incomeId;

    private Date startDate;

    private Date endDate;

    private BigDecimal value;

    private String origin;

    private String notes;
}
