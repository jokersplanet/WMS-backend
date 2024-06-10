package com.wms.pojo.dto.status;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-07 23:10
 */
@Data
public class StatusUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String sequence;
}
