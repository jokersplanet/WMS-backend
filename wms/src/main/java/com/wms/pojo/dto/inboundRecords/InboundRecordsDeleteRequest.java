package com.wms.pojo.dto.inboundRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-09 17:04
 */
@Data
public class InboundRecordsDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer inboundId;
}
