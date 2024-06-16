package com.wms.pojo.dto.income;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-06 22:55
 */
@Data
public class IncomeUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer incomeId;

    private Date time;

    private BigDecimal value;

    private String origin;

    private String notes;
}
