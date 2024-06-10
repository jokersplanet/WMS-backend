package com.wms.pojo.dto.unit;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 22:08
 */
@Data
public class UnitUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer unitId;

    private String unitName;
}
