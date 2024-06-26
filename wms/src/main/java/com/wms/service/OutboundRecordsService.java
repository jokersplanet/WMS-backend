package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsQueryRequest;
import com.wms.pojo.entity.OutboundRecords;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.pojo.vo.OutboundRecordsVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-09
 */
public interface OutboundRecordsService extends IService<OutboundRecords> {

    QueryWrapper<OutboundRecords> getQueryWrapper(OutboundRecordsQueryRequest outboundRecordsQueryRequest);

    OutboundRecordsVO getOutboundRecordsVO(OutboundRecords outboundRecords);
    Page<OutboundRecordsVO> getOutboundRecordsVOPage(Page<OutboundRecords> outboundRecordsPage);
}
