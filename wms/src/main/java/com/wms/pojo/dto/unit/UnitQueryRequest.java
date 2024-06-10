package com.wms.pojo.dto.unit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wms.common.QueryPageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 21:43
 */
@Data
public class UnitQueryRequest extends QueryPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer unitId;

    private String unitName;
}
