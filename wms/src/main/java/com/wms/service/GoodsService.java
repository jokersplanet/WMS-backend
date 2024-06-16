package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.pojo.dto.goods.GoodsQueryRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.pojo.entity.Goods;
import com.wms.pojo.vo.GoodsVO;

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

    Page<GoodsVO> getGoodsVOPage(Page<Goods> goodspage);

    GoodsVO getGoodsVO(Goods goods);
}
