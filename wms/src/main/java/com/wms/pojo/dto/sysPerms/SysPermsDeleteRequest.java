package com.wms.pojo.dto.sysPerms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-25 11:00
 */
@Data
public class SysPermsDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
}
