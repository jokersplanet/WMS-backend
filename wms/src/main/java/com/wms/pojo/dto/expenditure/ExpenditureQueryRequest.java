package com.wms.pojo.dto.expenditure;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author lwh
 * @create 2024-06-06 15:08
 */
@Data
public class ExpenditureQueryRequest extends QueryPageParam implements Serializable {

    private Integer expId;

    private Date startDate;

    private Date endDate;

    private BigDecimal value;

    private String destination;

    private String notes;

    private static final long serialVersionUID = 1L;
}
