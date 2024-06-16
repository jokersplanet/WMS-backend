package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsQueryRequest;
import com.wms.pojo.entity.ScrapRecords;
import com.wms.pojo.vo.ScrapRecordsVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-09
 */
public interface ScrapRecordsService extends IService<ScrapRecords> {

    QueryWrapper<ScrapRecords> getQueryWrapper(ScrapRecordsQueryRequest scrapRecordsQueryRequest);

    ScrapRecordsVO getScrapRecordsVO(ScrapRecords scrapRecords);

    Page<ScrapRecordsVO> getScrapRecordsVOPage(Page<ScrapRecords> scrapRecordsPage);
}
