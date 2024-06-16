package com.wms.pojo.dto.expenditure;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author lwh
 * @create 2024-06-06 22:01
 */
@Data
public class ExpenditureUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer expId;

    private Date time;

    private BigDecimal value;

    private String destination;

    private String notes;
}
