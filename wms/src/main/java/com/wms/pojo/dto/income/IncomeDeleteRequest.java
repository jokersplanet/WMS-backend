package com.wms.pojo.dto.income;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-06 22:52
 */
@Data
public class IncomeDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer incomeId;
}
