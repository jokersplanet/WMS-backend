package com.wms.pojo.dto.scrapRecords;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwh
 * @create 2024-06-09 20:06
 */
@Data
public class ScrapRecordsDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer scrapId;
}
