package com.wms.pojo.dto.income;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author lwh
 * @create 2024-06-06 22:48
 */
@Data
public class IncomeAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date time;

    private BigDecimal value;

    private String notes;
}
