package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.pojo.dto.goods.GoodsQueryRequest;
import com.wms.pojo.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lwh
 * @since 2024-06-08
 */
public interface GoodsService extends IService<Goods> {

    QueryWrapper<Goods> getQueryWrapper(GoodsQueryRequest goodsQueryRequest);
}
