package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.GoodsVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;

import java.util.List;

public interface GoodsService {


    ServiceVO getCode();

    /**
     * 查询商品数据并分页
     *
     * @param page
     * @param rows
     * @param codeOrName
     * @param goodsTypeId
     * @return
     */
    List<GoodsVo> getGoodsStockPage(Long page, Integer rows, String codeOrName, Integer goodsTypeId);

    long getGoodsTotal();

    List<GoodsVo> getGoodsPage(Long page, Integer rows, String goodsName, Integer goodsTypeId);

    Long getGoodsCountByGoodsTypeId(Integer goodsTypeId);

    void save(Goods goods);

    void update(Integer goodsId, Goods goods);

    void deleteById(Integer goodsId);

    List<GoodsVo> getNoInventoryQuantity(Long page, Integer rows, String nameOrCode);

    long getNoInventoryQuantityCount();

    List<GoodsVo> getHasInventoryQuantity(Long page, Integer rows, String nameOrCode);

    long getHasInventoryQuantityCount();

    void saveStock(Integer goodsId, Integer inventoryQuantity, Double purchasingPrice);

    ServiceVO deleteStock(Integer goodsId);

    List<GoodsVo> getGoodsListAlarm();
}
