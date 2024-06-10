package com.wms.pojo.dto.income;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-06 22:48
 */
@Data
public class IncomeAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String time;

    private BigDecimal value;

    private String notes;
}
