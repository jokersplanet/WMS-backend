package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.sysPerms.SysPermsQueryRequest;
import com.wms.pojo.entity.Rolelist;
import com.wms.pojo.entity.SysPerms;
import com.wms.mapper.SysPermsMapper;
import com.wms.service.SysPermsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-25
 */
@Service
public class SysPermsServiceImpl extends ServiceImpl<SysPermsMapper, SysPerms> implements SysPermsService {

    @Override
    public QueryWrapper<SysPerms> getQueryWrapper(SysPermsQueryRequest sysPermsQueryRequest) {
        QueryWrapper<SysPerms> queryWrapper = new QueryWrapper<>();
        if(sysPermsQueryRequest == null){
            return queryWrapper;
        }
        Integer id = sysPermsQueryRequest.getId();
        Integer parentId = sysPermsQueryRequest.getParentId();
        String name = sysPermsQueryRequest.getName();
        String perms = sysPermsQueryRequest.getPerms();
        String sortField = sysPermsQueryRequest.getSortField();
        String sortOrder = sysPermsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"id",id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(parentId),"parent_id",parentId);
        queryWrapper.eq(StringUtils.isNotBlank(name),"name",name);
        queryWrapper.eq(StringUtils.isNotBlank(perms),"perms",perms);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public List<String> findUserPermsByUserId(Long userId) {
        List<String> permsList = baseMapper.findPermsListByUserId(userId);
        return permsList;
    }
}
