package com.wms.pojo.dto.expenditure;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author lwh
 * @create 2024-06-06 15:43
 */
@Data
public class ExpenditureAddRequest implements Serializable {

    private Date time;

    private BigDecimal value;

    private String notes;

    private static final long serialVersionUID = 1L;
}
