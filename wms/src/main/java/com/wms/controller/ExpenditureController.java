package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.expenditure.ExpenditureAddRequest;
import com.wms.pojo.dto.expenditure.ExpenditureDeleteRequest;
import com.wms.pojo.dto.expenditure.ExpenditureQueryRequest;
import com.wms.pojo.dto.expenditure.ExpenditureUpdateRequest;
import com.wms.pojo.entity.Expenditure;
import com.wms.service.ExpenditureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author lwh
 * @since 2024-06-06
 */
@RestController
@RequestMapping("/expenditure")
@Slf4j
public class ExpenditureController {

    @Resource
    private ExpenditureService expenditureService;

    /**
     * 分页获取开支记录
     * @param expenditureQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<Expenditure>> listExpenditureByPage(@RequestBody ExpenditureQueryRequest expenditureQueryRequest){
        int current = expenditureQueryRequest.getCurrent();
        int size = expenditureQueryRequest.getPageSize();
        Page<Expenditure> page = expenditureService.page(new Page<>(current, size), expenditureService.getQueryWrapper(expenditureQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 根据时间分页获取开支记录
     * @param expenditureQueryRequest
     * @return
     */
    @PostMapping("/list/page/time")
    public Response<Page<Expenditure>> listExpenditureByTime(@RequestBody ExpenditureQueryRequest expenditureQueryRequest){
        Date startDate = expenditureQueryRequest.getStartDate();
        Date endDate = expenditureQueryRequest.getEndDate();
        QueryWrapper<Expenditure> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("time",startDate,endDate);
//        List<Expenditure> list = expenditureService.list(queryWrapper);
        Page<Expenditure> page = expenditureService.page(new Page<>(),expenditureService.getQueryWrapper(expenditureQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 新增开支记录
     * @param expenditureAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> expenditureAdd(@RequestBody ExpenditureAddRequest expenditureAddRequest){
        if(expenditureAddRequest == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Expenditure expenditure = new Expenditure();
        BeanUtils.copyProperties(expenditureAddRequest,expenditure);
        boolean save = expenditureService.save(expenditure);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultsSet.success(expenditure.getExpId());
    }

    /**
     * 删除开支记录
     * @param expenditureDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> expenditureDelete(@RequestBody ExpenditureDeleteRequest expenditureDeleteRequest){
        if(expenditureDeleteRequest == null || expenditureDeleteRequest.getExpId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = expenditureService.removeById(expenditureDeleteRequest.getExpId());
        return ResultsSet.success(result);
    }

    /**
     * 更新开支记录
     * @param expenditureUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> expenditureUpdate(@RequestBody ExpenditureUpdateRequest expenditureUpdateRequest){
        if(expenditureUpdateRequest == null || expenditureUpdateRequest.getExpId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Expenditure expenditure = new Expenditure();
        BeanUtils.copyProperties(expenditureUpdateRequest,expenditure);
        if(expenditureService.getById(expenditureUpdateRequest.getExpId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该开支，无法更新该记录信息");
        }else{
            boolean result = expenditureService.updateById(expenditure);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改记录失败");
            }
            return ResultsSet.success(result);
        }
    }
}
