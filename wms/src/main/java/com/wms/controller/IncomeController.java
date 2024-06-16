package com.wms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.ErrorCode;
import com.wms.common.Response;
import com.wms.common.ResultsSet;
import com.wms.exception.BusinessException;
import com.wms.pojo.dto.expenditure.ExpenditureAddRequest;
import com.wms.pojo.dto.expenditure.ExpenditureDeleteRequest;
import com.wms.pojo.dto.expenditure.ExpenditureUpdateRequest;
import com.wms.pojo.dto.income.IncomeAddRequest;
import com.wms.pojo.dto.income.IncomeDeleteRequest;
import com.wms.pojo.dto.income.IncomeQueryRequest;
import com.wms.pojo.dto.income.IncomeUpdateRequest;
import com.wms.pojo.entity.Expenditure;
import com.wms.pojo.entity.Income;
import com.wms.service.IncomeService;
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
 * @since 2024-06-06
 */
@RestController
@RequestMapping("/income")
@Slf4j
public class IncomeController {

    @Resource
    private IncomeService incomeService;

    /**
     * 分页获取收入记录
     * @param incomeQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public Response<Page<Income>> listIncomeByPage(@RequestBody IncomeQueryRequest incomeQueryRequest){
        int current = incomeQueryRequest.getCurrent();
        int size = incomeQueryRequest.getPageSize();
        Page<Income> page = incomeService.page(new Page<>(current, size), incomeService.getQueryWrapper(incomeQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 根据时间分页获取收入记录
     * 注意需要用户传入startDate和endDate
     * @param incomeQueryRequest
     * @return
     */
    @PostMapping("/list/page/time")
    public Response<Page<Income>> listIncomeByTime(@RequestBody IncomeQueryRequest incomeQueryRequest){
        Page<Income> page = incomeService.page(new Page<>(), incomeService.getQueryWrapper(incomeQueryRequest));
        return ResultsSet.success(page);
    }

    /**
     * 增加收入记录
     * @param incomeAddRequest
     * @return
     */
    @PostMapping("/add")
    public Response<Integer> incomeAdd(@RequestBody IncomeAddRequest incomeAddRequest){
        if(incomeAddRequest == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Income income = new Income();
        BeanUtils.copyProperties(incomeAddRequest,income);
        boolean save = incomeService.save(income);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultsSet.success(income.getIncomeId());
    }

    /**
     * 删除收入记录
     * @param incomeDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    public Response<Boolean> incomeDelete(@RequestBody IncomeDeleteRequest incomeDeleteRequest){
        if(incomeDeleteRequest == null || incomeDeleteRequest.getIncomeId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        boolean result = incomeService.removeById(incomeDeleteRequest.getIncomeId());
        return ResultsSet.success(result);
    }

    /**
     * 更新收入记录
     * @param incomeUpdateRequest
     * @return
     */
    @PostMapping("/update")
    public Response<Boolean> incomeUpdate(@RequestBody IncomeUpdateRequest incomeUpdateRequest){
        if(incomeUpdateRequest == null || incomeUpdateRequest.getIncomeId() <= 0){
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }
        Income income = new Income();
        BeanUtils.copyProperties(incomeUpdateRequest,income);
        if(incomeService.getById(incomeUpdateRequest.getIncomeId()) == null){
            throw new BusinessException(ErrorCode.REQUEST_ERROR,"不存在该收入，无法更新该记录");
        }else {
            boolean result = incomeService.updateById(income);
            if(!result){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"修改记录失败");
            }
            return ResultsSet.success(result);
        }
    }
}
