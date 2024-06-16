package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.unit.UnitAddRequest;
import com.wms.pojo.dto.unit.UnitDeleteRequest;
import com.wms.pojo.dto.unit.UnitQueryRequest;
import com.wms.pojo.dto.unit.UnitUpdateRequest;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.Unit;
import com.wms.service.GoodsService;
import com.wms.service.UnitService;
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
@RequestMapping("/unit")
@Slf4j
public class UnitController {

    @Resource
    private UnitService unitService;
    @Resource
    private GoodsService goodsService;

    /**
     * 分页获取单位信息
     * @param unitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<Unit>> listUnitByPage(@RequestBody UnitQueryRequest unitQueryRequest){
        int current = unitQueryRequest.getCurrent();
        int size = unitQueryRequest.getPageSize();
        Page<Unit> page = unitService.page(new Page<>(current, size), unitService.getQueryWrapper(unitQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 增加单位记录
     * @param unitAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> unitAdd(@RequestBody UnitAddRequest unitAddRequest){
        if(unitAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Unit unit = new Unit();
        BeanUtils.copyProperties(unitAddRequest,unit);
        boolean save = unitService.save(unit);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultsSet.success(unit.getUnitId());
    }

    /**
     * 删除单位记录
     * @param unitDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> unitDelete(@RequestBody UnitDeleteRequest unitDeleteRequest){
        if(unitDeleteRequest == null || unitDeleteRequest.getUnitId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getUnitId,unitDeleteRequest.getUnitId());
        if(goodsService.getOne(queryWrapper,false) != null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"该单位下存在货物无法删除");
        }else{
            boolean result = unitService.removeById(unitDeleteRequest.getUnitId());
            return ResultsSet.success(result);
        }
    }

    /**
     * 更新单位记录
     * @param unitUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> unitUpdate(@RequestBody UnitUpdateRequest unitUpdateRequest){
        if(unitUpdateRequest == null || unitUpdateRequest.getUnitId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Unit unit = new Unit();
        BeanUtils.copyProperties(unitUpdateRequest,unit);
        if(unitService.getById(unitUpdateRequest.getUnitId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该单位，无法修改该单位信息");
        } else{
            boolean result = unitService.updateById(unit);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改单位记录失败");
            }
            return ResultsSet.success(result);
        }
    }
}
