package com.wms.pojo.dto.outboundRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-09 19:51
 */
@Data
public class OutboundRecordsDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer outboundId;
}
