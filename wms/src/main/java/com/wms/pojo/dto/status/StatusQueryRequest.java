package com.wms.pojo.dto.status;

import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-07 22:08
 */
@Data
public class StatusQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String sequence;
}
