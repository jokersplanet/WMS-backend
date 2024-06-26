package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsAddRequest;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsDeleteRequest;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsQueryRequest;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsUpdateRequest;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.ScrapRecords;
import com.wms.pojo.entity.User;
import com.wms.pojo.vo.ScrapRecordsVO;
import com.wms.service.*;
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
@RequestMapping("/scrap-records")
@Slf4j
public class ScrapRecordsController {

    @Resource
    private ScrapRecordsService scrapRecordsService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private UserService userService;

    /**
     * 分页获取废弃记录信息
     * @param scrapRecordsQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @PreAuthorize("hasAuthority('scrapRecords.listPage')")
    public Response<Page<ScrapRecordsVO>> listScrapboundsByPage(@RequestBody ScrapRecordsQueryRequest scrapRecordsQueryRequest){
        int current = scrapRecordsQueryRequest.getCurrent();
        int size = scrapRecordsQueryRequest.getPageSize();
        Page<ScrapRecords> scrapRecordsPage = scrapRecordsService.page(new Page<>(current, size), scrapRecordsService.getQueryWrapper(scrapRecordsQueryRequest));
        Page<ScrapRecordsVO> scrapRecordsVOPage = scrapRecordsService.getScrapRecordsVOPage(scrapRecordsPage);
        return Response.success(scrapRecordsVOPage);
    }

    /**
     * 根据时间分页获取废弃记录信息
     * 注意需要用户传入startDate和endDate
     * @param scrapRecordsQueryRequest
     * @return
     */
    @PostMapping("/list/page/time")
    @PreAuthorize("hasAuthority('scrapRecords.listPage.time')")
    public Response<Page<ScrapRecordsVO>> listScrapboundsByTime(@RequestBody ScrapRecordsQueryRequest scrapRecordsQueryRequest){
        Page<ScrapRecords> scrapRecordsPage = scrapRecordsService.page(new Page<>(), scrapRecordsService.getQueryWrapper(scrapRecordsQueryRequest));
        Page<ScrapRecordsVO> scrapRecordsVOPage = scrapRecordsService.getScrapRecordsVOPage(scrapRecordsPage);
        return Response.success(scrapRecordsVOPage);
    }

    /**
     * 增加废弃记录
     * @param scrapRecordsAddRequest
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('scrapRecords.add')")
    public Response<Integer> scrapRecordsAdd(@RequestBody ScrapRecordsAddRequest scrapRecordsAddRequest){
        if(scrapRecordsAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        ScrapRecords scrapRecords = new ScrapRecords();
        BeanUtils.copyProperties(scrapRecordsAddRequest,scrapRecords);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount,scrapRecordsAddRequest.getUserAccount());
        if(goodsService.getById(scrapRecordsAddRequest.getGoodsId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该货物");
        }else if(userService.getOne(queryWrapper) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该账户");
        }else if(warehouseService.getById(scrapRecordsAddRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，请先创建该仓库或选择其他仓库");
        }else{
            boolean save = scrapRecordsService.save(scrapRecords);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return Response.success(scrapRecords.getScrapId());
        }
    }

    /**
     * 删除废弃记录
     * @param scrapRecordsDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('scrapRecords.delete')")
    public Response<Boolean> scrapRecordsDelete(@RequestBody ScrapRecordsDeleteRequest scrapRecordsDeleteRequest){
        if(scrapRecordsDeleteRequest == null || scrapRecordsDeleteRequest.getScrapId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = scrapRecordsService.removeById(scrapRecordsDeleteRequest.getScrapId());
        return Response.success(result);
    }

    /**
     * 更新废弃记录
     * @param scrapRecordsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('scrapRecords.update')")
    public Response<Boolean> scrapRecordsUpdate(@RequestBody ScrapRecordsUpdateRequest scrapRecordsUpdateRequest) {
        if (scrapRecordsUpdateRequest == null || scrapRecordsUpdateRequest.getScrapId() <= 0) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        ScrapRecords scrapRecords = new ScrapRecords();
        BeanUtils.copyProperties(scrapRecordsUpdateRequest, scrapRecords);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount, scrapRecordsUpdateRequest.getUserAccount());
        if(scrapRecordsService.getById(scrapRecordsUpdateRequest.getScrapId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该记录，无法更新记录信息");
        } else if(goodsService.getById(scrapRecordsUpdateRequest.getGoodsId()) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该货物");
        } else if (userService.getOne(queryWrapper) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该账户");
        } else if (warehouseService.getById(scrapRecordsUpdateRequest.getWareId()) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该仓库，请先创建该仓库或选择其他仓库");
        } else {
            boolean result = scrapRecordsService.updateById(scrapRecords);
            if (!result) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改出库记录失败");
            }
            return Response.success(result);
        }
    }
}
