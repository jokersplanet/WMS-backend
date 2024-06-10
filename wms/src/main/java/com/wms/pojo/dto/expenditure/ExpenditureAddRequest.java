package com.wms.pojo.dto.expenditure;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-06 15:43
 */
@Data
public class ExpenditureAddRequest implements Serializable {

    private String time;

    private BigDecimal value;

    private String notes;

    private static final long serialVersionUID = 1L;
}
