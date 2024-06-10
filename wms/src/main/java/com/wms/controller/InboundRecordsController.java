package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.goods.GoodsAddRequest;
import com.wms.pojo.dto.goods.GoodsDeleteRequest;
import com.wms.pojo.dto.goods.GoodsQueryRequest;
import com.wms.pojo.dto.goods.GoodsUpdateRequest;
import com.wms.pojo.dto.inboundRecords.InboundRecordsAddRequest;
import com.wms.pojo.dto.inboundRecords.InboundRecordsDeleteRequest;
import com.wms.pojo.dto.inboundRecords.InboundRecordsQueryRequest;
import com.wms.pojo.dto.inboundRecords.InboundRecordsUpdateRequest;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.InboundRecords;
import com.wms.pojo.entity.Orders;
import com.wms.pojo.entity.User;
import com.wms.service.GoodsService;
import com.wms.service.InboundRecordsService;
import com.wms.service.UserService;
import com.wms.service.WarehouseService;
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
@RequestMapping("/inbound-records")
@Slf4j
public class InboundRecordsController {

    @Resource
    private InboundRecordsService inboundRecordsService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private UserService userService;

    /**
     * 分页获取入库记录信息
     * @param inboundRecordsQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<InboundRecords>> listInboundsByPage(@RequestBody InboundRecordsQueryRequest inboundRecordsQueryRequest){
        int current = inboundRecordsQueryRequest.getCurrent();
        int size = inboundRecordsQueryRequest.getPageSize();
        Page<InboundRecords> page = inboundRecordsService.page(new Page<>(current, size), inboundRecordsService.getQueryWrapper(inboundRecordsQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 增加入库记录
     * @param inboundRecordsAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> inboundsAdd(@RequestBody InboundRecordsAddRequest inboundRecordsAddRequest){
        if(inboundRecordsAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        InboundRecords inboundRecords = new InboundRecords();
        BeanUtils.copyProperties(inboundRecordsAddRequest,inboundRecords);
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getGoodsName,inboundRecordsAddRequest.getGoodsName());
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(User::getUserAccount,inboundRecordsAddRequest.getUserAccount());
        if(goodsService.getOne(queryWrapper) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该货物");
        }else if(userService.getOne(queryWrapper1) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该账户");
        }else if(warehouseService.getById(inboundRecordsAddRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，请先创建该仓库或选择其他仓库");
        }else{
            boolean save = inboundRecordsService.save(inboundRecords);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return ResultsSet.success(inboundRecords.getInboundId());
        }
    }

    /**
     * 删除入库记录
     * @param inboundRecordsDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> inboundsDelete(@RequestBody InboundRecordsDeleteRequest inboundRecordsDeleteRequest){
        if(inboundRecordsDeleteRequest == null || inboundRecordsDeleteRequest.getInboundId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = inboundRecordsService.removeById(inboundRecordsDeleteRequest.getInboundId());
        return ResultsSet.success(result);
    }

    /**
     * 修改入库记录
     * @param inboundRecordsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> inboundsUpdate(@RequestBody InboundRecordsUpdateRequest inboundRecordsUpdateRequest) {
        if (inboundRecordsUpdateRequest == null || inboundRecordsUpdateRequest.getInboundId() <= 0) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        InboundRecords inboundRecords = new InboundRecords();
        BeanUtils.copyProperties(inboundRecordsUpdateRequest, inboundRecords);
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getGoodsName, inboundRecordsUpdateRequest.getGoodsName());
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(User::getUserAccount, inboundRecordsUpdateRequest.getUserAccount());
        if (goodsService.getOne(queryWrapper) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该货物");
        } else if (userService.getOne(queryWrapper1) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该账户");
        } else if (warehouseService.getById(inboundRecordsUpdateRequest.getWareId()) == null) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不存在该仓库，请先创建该仓库或选择其他仓库");
        } else {
            boolean result = inboundRecordsService.updateById(inboundRecords);
            if (!result) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改入库记录失败");
            }
            return ResultsSet.success(result);
        }
    }
}
