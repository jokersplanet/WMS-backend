package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsAddRequest;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsDeleteRequest;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsQueryRequest;
import com.wms.pojo.dto.outboundRecords.OutboundRecordsUpdateRequest;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsAddRequest;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsDeleteRequest;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsQueryRequest;
import com.wms.pojo.dto.scrapRecords.ScrapRecordsUpdateRequest;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.OutboundRecords;
import com.wms.pojo.entity.ScrapRecords;
import com.wms.pojo.entity.User;
import com.wms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    public Response<Page<ScrapRecords>> listScrapboundsByPage(@RequestBody ScrapRecordsQueryRequest scrapRecordsQueryRequest){
        int current = scrapRecordsQueryRequest.getCurrent();
        int size = scrapRecordsQueryRequest.getPageSize();
        Page<ScrapRecords> page = scrapRecordsService.page(new Page<>(current, size), scrapRecordsService.getQueryWrapper(scrapRecordsQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 增加废弃记录
     * @param scrapRecordsAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> scrapRecordsAdd(@RequestBody ScrapRecordsAddRequest scrapRecordsAddRequest){
        if(scrapRecordsAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        ScrapRecords scrapRecords = new ScrapRecords();
        BeanUtils.copyProperties(scrapRecordsAddRequest,scrapRecords);
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getGoodsName,scrapRecordsAddRequest.getGoodsName());
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(User::getUserAccount,scrapRecordsAddRequest.getUserAccount());
        if(goodsService.getOne(queryWrapper) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该货物");
        }else if(userService.getOne(queryWrapper1) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该账户");
        }else if(warehouseService.getById(scrapRecordsAddRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，请先创建该仓库或选择其他仓库");
        }else{
            boolean save = scrapRecordsService.save(scrapRecords);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return ResultsSet.success(scrapRecords.getScrapId());
        }
    }

    /**
     * 删除废弃记录
     * @param scrapRecordsDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> scrapRecordsDelete(@RequestBody ScrapRecordsDeleteRequest scrapRecordsDeleteRequest){
        if(scrapRecordsDeleteRequest == null || scrapRecordsDeleteRequest.getScrapId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = scrapRecordsService.removeById(scrapRecordsDeleteRequest.getScrapId());
        return ResultsSet.success(result);
    }

    /**
     * 更新废弃记录
     * @param scrapRecordsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> scrapRecordsUpdate(@RequestBody ScrapRecordsUpdateRequest scrapRecordsUpdateRequest) {
        if (scrapRecordsUpdateRequest == null || scrapRecordsUpdateRequest.getScrapId() <= 0) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        ScrapRecords scrapRecords = new ScrapRecords();
        BeanUtils.copyProperties(scrapRecordsUpdateRequest, scrapRecords);
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getGoodsName, scrapRecordsUpdateRequest.getGoodsName());
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(User::getUserAccount, scrapRecordsUpdateRequest.getUserAccount());
        if (goodsService.getOne(queryWrapper) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该货物");
        } else if (userService.getOne(queryWrapper1) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该账户");
        } else if (warehouseService.getById(scrapRecordsUpdateRequest.getWareId()) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该仓库，请先创建该仓库或选择其他仓库");
        } else {
            boolean result = scrapRecordsService.updateById(scrapRecords);
            if (!result) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改出库记录失败");
            }
            return ResultsSet.success(result);
        }
    }
}
