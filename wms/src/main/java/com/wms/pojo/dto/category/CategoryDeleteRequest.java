package com.wms.pojo.dto.category;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-08 21:05
 */
@Data
public class CategoryDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cateId;
}
