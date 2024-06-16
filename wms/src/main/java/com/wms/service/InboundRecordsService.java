package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.pojo.dto.inboundRecords.InboundRecordsQueryRequest;
import com.wms.pojo.entity.InboundRecords;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.pojo.vo.InboundRecordsVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-09
 */
public interface InboundRecordsService extends IService<InboundRecords> {

    QueryWrapper<InboundRecords> getQueryWrapper(InboundRecordsQueryRequest inboundRecordsQueryRequest);

    InboundRecordsVO getInboundRecordsVO(InboundRecords inboundRecords);
    Page<InboundRecordsVO> getInboundRecordsVOPage(Page<InboundRecords> inboundRecordsPage);
}
