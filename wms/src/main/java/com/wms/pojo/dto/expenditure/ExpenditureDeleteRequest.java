package com.wms.pojo.dto.expenditure;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-06 16:10
 */
@Data
public class ExpenditureDeleteRequest implements Serializable {

    private Integer expId;

    private static final long serialVersionUID = 1L;
}
