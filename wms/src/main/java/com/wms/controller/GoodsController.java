package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.goods.GoodsAddRequest;
import com.wms.pojo.dto.goods.GoodsDeleteRequest;
import com.wms.pojo.dto.goods.GoodsQueryRequest;
import com.wms.pojo.dto.goods.GoodsUpdateRequest;
import com.wms.pojo.entity.Expenditure;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.vo.GoodsVO;
import com.wms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Date;

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
    private WarehouseService warehouseService;

    /**
     * 分页获取货物信息
     * @param goodsQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @PreAuthorize("hasAuthority('goods.listPage')")
    public Response<Page<GoodsVO>> listGoodsByPage(@RequestBody GoodsQueryRequest goodsQueryRequest){
        int current = goodsQueryRequest.getCurrent();
        int size = goodsQueryRequest.getPageSize();
        Page<Goods> goodspage = goodsService.page(new Page<>(current, size), goodsService.getQueryWrapper(goodsQueryRequest));
        Page<GoodsVO> goodsVOPage = goodsService.getGoodsVOPage(goodspage);
        return Response.success(goodsVOPage);
    }

    /**
     * 根据入库时间分页获取货物信息
     * 注意需要用户传入startDate和endDate
     * @param goodsQueryRequest
     * @return
     */
    @PostMapping("/list/page/inboundTime")
    @PreAuthorize("hasAuthority('goods.listPage.inboundTime')")
    public Response<Page<GoodsVO>> listGoodsByInboundTime(@RequestBody GoodsQueryRequest goodsQueryRequest){
//        Date startDate = goodsQueryRequest.getStartDate();
//        Date endDate = goodsQueryRequest.getEndDate();
        Page<Goods> goodspage = goodsService.page(new Page<>(), goodsService.getQueryWrapper(goodsQueryRequest));
        Page<GoodsVO> goodsVOPage = goodsService.getGoodsVOPage(goodspage);
        return Response.success(goodsVOPage);
    }

    /**
     * 根据出库时间分页获取货物信息
     * 注意需要用户传入startDate和endDate
     * @param goodsQueryRequest
     * @return
     */
    @PostMapping("/list/page/outboundTime")
    @PreAuthorize("hasAuthority('goods.listPage.outboundTime')")
    public Response<Page<GoodsVO>> listGoodsByOutboundTime(@RequestBody GoodsQueryRequest goodsQueryRequest){
        Date startDate = goodsQueryRequest.getStartDate();
        Date endDate = goodsQueryRequest.getEndDate();
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("outbound_time",startDate,endDate);
        Page<Goods> goodspage = goodsService.page(new Page<>(),queryWrapper);
        Page<GoodsVO> goodsVOPage = goodsService.getGoodsVOPage(goodspage);
        return Response.success(goodsVOPage);
    }

    /**
     * 根据仓库分页获取货物信息
     * 注意需要用户传入仓库id
     * @param goodsQueryRequest
     * @return
     */
    @PostMapping("/list/page/wareId")
    @PreAuthorize("hasAuthority('goods.listPage.wareId')")
    public Response<Page<GoodsVO>> listGoodsByWareId(@RequestBody GoodsQueryRequest goodsQueryRequest){
//        Integer wareId = goodsQueryRequest.getWareId();
//        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("ware_id",wareId);
        Page<Goods> goodspage = goodsService.page(new Page<>(),goodsService.getQueryWrapper(goodsQueryRequest));
        Page<GoodsVO> goodsVOPage = goodsService.getGoodsVOPage(goodspage);
        return Response.success(goodsVOPage);
    }

    /**
     * 增加货物记录
     * @param goodsAddRequest
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('goods.add')")
    public Response<Integer> goodsAdd(@RequestBody GoodsAddRequest goodsAddRequest){
        if(goodsAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsAddRequest,goods);
        if(typeclassService.getById(goodsAddRequest.getUpperClassId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在上一类，请先创建该类或者选择其他类");
        }else if(typeclassService.getById(goodsAddRequest.getLowerClassId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在下一类，请先创建该类或者选择其他类");
        }else if(unitService.getById(goodsAddRequest.getUnitId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该单位，请先创建该单位或选择其他单位");
        }else if(warehouseService.getById(goodsAddRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，请先创建该仓库或选择其他仓库");
        }else{
            boolean save = goodsService.save(goods);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return Response.success(goods.getGoodsId());
        }
    }

    /**
     * 删除货物记录
     * @param goodsDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('goods.delete')")
    public Response<Boolean> goodsDelete(@RequestBody GoodsDeleteRequest goodsDeleteRequest){
        if(goodsDeleteRequest == null || goodsDeleteRequest.getGoodsId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = goodsService.removeById(goodsDeleteRequest.getGoodsId());
        return Response.success(result);
    }

    /**
     * 更新货物记录
     * @param goodsUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('goods.update')")
    public Response<Boolean> goodsUpdate(@RequestBody GoodsUpdateRequest goodsUpdateRequest){
        if(goodsUpdateRequest == null || goodsUpdateRequest.getGoodsId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsUpdateRequest,goods);
        if(goodsService.getById(goodsUpdateRequest.getGoodsId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"该货物不存在，无法更新货物信息");
        }else if(typeclassService.getById(goodsUpdateRequest.getUpperClassId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在上一类，请先创建该类或者选择其他类");
        }else if(typeclassService.getById(goodsUpdateRequest.getLowerClassId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在下一类，请先创建该类或者选择其他类");
        }else if(unitService.getById(goodsUpdateRequest.getUnitId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该单位，请先创建该单位或选择其他单位");
        }else if(warehouseService.getById(goodsUpdateRequest.getWareId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该仓库，请先创建该仓库或选择其他仓库");
        }else{
            boolean result = goodsService.updateById(goods);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改货物记录失败");
            }
            return Response.success(result);
        }
    }
}
