package com.wms.pojo.dto.expenditure;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lwh
 * @create 2024-06-06 15:08
 */
@Data
public class ExpenditureQueryRequest extends QueryPageParam implements Serializable {

    private Integer expId;

    private String time;

    private BigDecimal value;

    private String destination;

    private String notes;

    private static final long serialVersionUID = 1L;
}
