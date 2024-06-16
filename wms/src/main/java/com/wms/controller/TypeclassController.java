package com.wms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.typeclass.TypeclassAddRequest;
import com.wms.pojo.dto.typeclass.TypeclassDeleteRequest;
import com.wms.pojo.dto.typeclass.TypeclassQueryRequest;
import com.wms.pojo.dto.typeclass.TypeclassUpdateRequest;
import com.wms.pojo.entity.Typeclass;
import com.wms.service.TypeclassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 货物种类 前端控制器
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/typeclass")
@Slf4j
public class TypeclassController {

    @Resource
    private TypeclassService typeclassService;

    /**
     * 分页获取类信息
     * @param typeclassQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<Typeclass>> listTypeclassByPage(@RequestBody TypeclassQueryRequest typeclassQueryRequest){
        int current = typeclassQueryRequest.getCurrent();
        int size = typeclassQueryRequest.getPageSize();
        Page<Typeclass> page = typeclassService.page(new Page<>(current, size), typeclassService.getQueryWrapper(typeclassQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 增加类记录
     * @param typeclassAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> typeclassAdd(@RequestBody TypeclassAddRequest typeclassAddRequest){
        if(typeclassAddRequest == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Typeclass typeclass = new Typeclass();
        BeanUtils.copyProperties(typeclassAddRequest,typeclass);
            boolean save = typeclassService.save(typeclass);
            if(!save){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return ResultsSet.success(typeclass.getClassId());
    }

    /**
     * 删除类记录
     * @param typeclassDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> typeclassDelete(@RequestBody TypeclassDeleteRequest typeclassDeleteRequest){
        if(typeclassDeleteRequest == null || typeclassDeleteRequest.getClassId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = typeclassService.removeById(typeclassDeleteRequest.getClassId());
        return ResultsSet.success(result);
    }

    /**
     * 修改类信息
     * @param typeclassUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> typeclassUpdate(@RequestBody TypeclassUpdateRequest typeclassUpdateRequest){
        if(typeclassUpdateRequest == null || typeclassUpdateRequest.getClassId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Typeclass typeclass = new Typeclass();
        BeanUtils.copyProperties(typeclassUpdateRequest,typeclass);
        if(typeclassService.getById(typeclassUpdateRequest.getClassId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该类，无法修改该类信息");
        }else{
            boolean result = typeclassService.updateById(typeclass);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改类信息失败");
            }
            return ResultsSet.success(result);
        }
    }
}
