package com.wms.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.constant.CommonConstant;
import com.wms.pojo.dto.goods.GoodsQueryRequest;
import com.wms.mapper.GoodsMapper;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.entity.Typeclass;
import com.wms.pojo.entity.Unit;
import com.wms.pojo.vo.GoodsVO;
import com.wms.pojo.vo.TypeClassVO;
import com.wms.pojo.vo.UnitVO;
import com.wms.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wms.service.TypeclassService;
import com.wms.service.UnitService;
import com.wms.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private UnitService unitService;
    @Resource
    private TypeclassService typeclassService;
    @Override
    public QueryWrapper<Goods> getQueryWrapper(GoodsQueryRequest goodsQueryRequest) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if(goodsQueryRequest == null){
            return queryWrapper;
        }
        Integer id = goodsQueryRequest.getGoodsId();
        String goodsName = goodsQueryRequest.getGoodsName();
        Integer unitId = goodsQueryRequest.getUnitId();
        Integer wareId = goodsQueryRequest.getWareId();
        Integer upperClassId = goodsQueryRequest.getUpperClassId();
        Integer lowerClassId = goodsQueryRequest.getLowerClassId();
        Date startDate = goodsQueryRequest.getStartDate();
        Date endDate = goodsQueryRequest.getEndDate();
        String sortField = goodsQueryRequest.getSortField();
        String sortOrder = goodsQueryRequest.getSortOrder();
        queryWrapper.eq(ObjectUtils.isNotEmpty(id),"goods_id",id);
        queryWrapper.eq(StringUtils.isNotBlank(goodsName),"goods_name",goodsName);
        queryWrapper.eq(ObjectUtils.isNotEmpty(unitId),"unit_id",unitId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(wareId),"ware_id",wareId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(upperClassId),"upper_class_id",upperClassId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(lowerClassId),"lower_class_id",lowerClassId);
        queryWrapper.between("inbound_time",startDate,endDate);
        queryWrapper.eq("is_deleted",true);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return queryWrapper;
    }

    @Override
    public Page<GoodsVO> getGoodsVOPage(Page<Goods> goodspage) {
        List<Goods> goodsList = goodspage.getRecords();
        Page<GoodsVO> goodsVOPage = new Page<>(goodspage.getCurrent(), goodspage.getSize(), goodspage.getTotal());
        if(CollUtil.isEmpty(goodsList)){
            return goodsVOPage;
        }
        List<GoodsVO> goodsVOList = goodsList.stream().map(goods -> {
            return getGoodsVO(goods);
        }).collect(Collectors.toList());
        goodsVOPage.setRecords(goodsVOList);
        return goodsVOPage;
    }

    @Override
    public GoodsVO getGoodsVO(Goods goods) {
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods,goodsVO);
        Unit unit = unitService.getById(goods.getUnitId());
        UnitVO unitVO = new UnitVO();
        BeanUtils.copyProperties(unit,unitVO);
        goodsVO.setUnitVO(unitVO);
        Typeclass upperclass = typeclassService.getById(goods.getUpperClassId());
        TypeClassVO upperclassVO = new TypeClassVO();
        BeanUtils.copyProperties(upperclass,upperclassVO);
        goodsVO.setUpperClassVO(upperclassVO);
        Typeclass lowerclass = typeclassService.getById(goods.getLowerClassId());
        TypeClassVO lowerclassVO = new TypeClassVO();
        BeanUtils.copyProperties(lowerclass,lowerclassVO);
        goodsVO.setUpperClassVO(lowerclassVO);
        return goodsVO;
    }
}
