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
import com.wms.pojo.dto.unit.UnitAddRequest;
import com.wms.pojo.dto.unit.UnitQueryRequest;
import com.wms.pojo.dto.unit.UnitUpdateRequest;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.Orders;
import com.wms.pojo.entity.Unit;
import com.wms.pojo.entity.Warehouse;
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
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Resource
    private GoodsService goodsService;
    @Resource
    private TypeclassService typeclassService;
    @Resource
    private UnitService unitService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private WarehouseService warehouseService;

    /**
     * 分页获取货物信息
     * @param goodsQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<Goods>> listGoodsByPage(@RequestBody GoodsQueryRequest goodsQueryRequest){
        int current = goodsQueryRequest.getCurrent();
        int size = goodsQueryRequest.getPageSize();
        Page<Goods> page = goodsService.page(new Page<>(current, size), goodsService.getQueryWrapper(goodsQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 增加货物记录
     * @param goodsAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> goodsAdd(@RequestBody GoodsAddRequest goodsAddRequest){
        if(goodsAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsAddRequest,goods);
        if(typeclassService.getById(goodsAddRequest.getClassId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该小类，请先创建该小类或选择其他小类");
        }else if(unitService.getById(goodsAddRequest.getUnitId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该单位，请先创建该单位或选择其他单位");
        }else if(categoryService.getById(goodsAddRequest.getCateId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该大类，请先创建该大类或选择其他大类");
        }else if(warehouseService.getById(goodsAddRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，请先创建该仓库或选择其他仓库");
        }else{
            boolean save = goodsService.save(goods);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return ResultsSet.success(goods.getGoodsId());
        }
    }

    /**
     * 删除货物记录
     * @param goodsDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> goodsDelete(@RequestBody GoodsDeleteRequest goodsDeleteRequest){
        if(goodsDeleteRequest == null || goodsDeleteRequest.getGoodsId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = goodsService.removeById(goodsDeleteRequest.getGoodsId());
        return ResultsSet.success(result);
    }

    /**
     * 更新货物记录
     * @param goodsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> goodsUpdate(@RequestBody GoodsUpdateRequest goodsUpdateRequest){
        if(goodsUpdateRequest == null || goodsUpdateRequest.getGoodsId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsUpdateRequest,goods);
        if(typeclassService.getById(goodsUpdateRequest.getClassId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该小类，请先创建该小类或选择其他小类");
        }else if(unitService.getById(goodsUpdateRequest.getUnitId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该单位，请先创建该单位或选择其他单位");
        }else if(categoryService.getById(goodsUpdateRequest.getCateId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该大类，请先创建该大类或选择其他大类");
        }else if(warehouseService.getById(goodsUpdateRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，请先创建该仓库或选择其他仓库");
        }else{
            boolean result = goodsService.updateById(goods);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改货物记录失败");
            }
            return ResultsSet.success(result);
        }
    }
}
