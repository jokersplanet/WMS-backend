package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.inboundRecords.InboundRecordsQueryRequest;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsAddRequest;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsDeleteRequest;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsQueryRequest;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsUpdateRequest;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.InboundRecords;
import com.wms.pojo.entity.OutboundRecords;
import com.wms.pojo.entity.User;
import com.wms.pojo.vo.InboundRecordsVO;
import com.wms.pojo.vo.OutboundRecordsVO;
import com.wms.service.GoodsService;
import com.wms.service.OutboundRecordsService;
import com.wms.service.UserService;
import com.wms.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lwh
 * @since 2024-06-09
 */
@RestController
@RequestMapping("/outbound-records")
@Slf4j
public class OutboundRecordsController {

    @Resource
    private OutboundRecordsService outboundRecordsService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private UserService userService;

    /**
     * 分页获取出库记录信息
     * @param outboundRecordsQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @PreAuthorize("hasAuthority('outboundRecords.listPage')")
    public Response<Page<OutboundRecordsVO>> listOutboundsByPage(@RequestBody OutboundRecordsQueryRequest outboundRecordsQueryRequest){
        int current = outboundRecordsQueryRequest.getCurrent();
        int size = outboundRecordsQueryRequest.getPageSize();
        Page<OutboundRecords> outboundRecordsPage = outboundRecordsService.page(new Page<>(current, size), outboundRecordsService.getQueryWrapper(outboundRecordsQueryRequest));
        Page<OutboundRecordsVO> outboundRecordsVOPage = outboundRecordsService.getOutboundRecordsVOPage(outboundRecordsPage);
        return Response.success(outboundRecordsVOPage);
    }

    /**
     * 根据时间分页获取出库记录信息
     * 注意需要用户传入startDate和endDate
     * @param outboundRecordsQueryRequest
     * @return
     */
    @PostMapping("/list/page/time")
    @PreAuthorize("hasAuthority('outboundRecords.listPage.time')")
    public Response<Page<OutboundRecordsVO>> listOutboundsByTime(@RequestBody OutboundRecordsQueryRequest outboundRecordsQueryRequest){
        Page<OutboundRecords> outboundRecordsPage = outboundRecordsService.page(new Page<>(), outboundRecordsService.getQueryWrapper(outboundRecordsQueryRequest));
        Page<OutboundRecordsVO> outboundRecordsVOPage =  outboundRecordsService.getOutboundRecordsVOPage(outboundRecordsPage);
        return Response.success(outboundRecordsVOPage);
    }


    /**
     * 增加出库记录
     * @param outboundRecordsAddRequest
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('outboundRecords.add')")
    public Response<Integer> outboundsAdd(@RequestBody OutboundRecordsAddRequest outboundRecordsAddRequest){
        if(outboundRecordsAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        OutboundRecords outboundRecords = new OutboundRecords();
        BeanUtils.copyProperties(outboundRecordsAddRequest,outboundRecords);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount,outboundRecordsAddRequest.getUserAccount());
        if(goodsService.getById(outboundRecordsAddRequest.getGoodsId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该货物");
        }else if(userService.getOne(queryWrapper) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该账户");
        }else if(warehouseService.getById(outboundRecordsAddRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，请先创建该仓库或选择其他仓库");
        }else{
            boolean save = outboundRecordsService.save(outboundRecords);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return Response.success(outboundRecords.getOutboundId());
        }
    }

    /**
     * 删除出库记录
     * @param outboundRecordsDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('outboundRecords.delete')")
    public Response<Boolean> outboundsDelete(@RequestBody OutboundRecordsDeleteRequest outboundRecordsDeleteRequest){
        if(outboundRecordsDeleteRequest == null || outboundRecordsDeleteRequest.getOutboundId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = outboundRecordsService.removeById(outboundRecordsDeleteRequest.getOutboundId());
        return Response.success(result);
    }

    /**
     * 修改出库记录
     * @param outboundRecordsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('outboundRecords.update')")
    public Response<Boolean> outboundsUpdate(@RequestBody OutboundRecordsUpdateRequest outboundRecordsUpdateRequest) {
        if (outboundRecordsUpdateRequest == null || outboundRecordsUpdateRequest.getOutboundId() <= 0) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        OutboundRecords outboundRecords = new OutboundRecords();
        BeanUtils.copyProperties(outboundRecordsUpdateRequest, outboundRecords);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount, outboundRecordsUpdateRequest.getUserAccount());
        if (outboundRecordsService.getById(outboundRecordsUpdateRequest.getOutboundId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该记录，无法更新该记录信息");
        } else if(goodsService.getById(outboundRecordsUpdateRequest.getGoodsId()) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该货物");
        } else if (userService.getOne(queryWrapper) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该账户");
        } else if (warehouseService.getById(outboundRecordsUpdateRequest.getWareId()) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该仓库，请先创建该仓库或选择其他仓库");
        } else {
            boolean result = outboundRecordsService.updateById(outboundRecords);
            if (!result) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改出库记录失败");
            }
            return Response.success(result);
        }
    }
}
