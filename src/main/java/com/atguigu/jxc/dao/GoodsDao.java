package com.atguigu.jxc.dao;

import com.atguigu.jxc.domain.GoodsVo;
import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();


    List<GoodsVo> getGoodsStockPage(@Param("offset") Long offset, @Param("rows") Integer rows, @Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);

    Long findGoodsCount();


    List<GoodsVo> getGoodsPage(@Param("offset") Long offset, @Param("rows") Integer rows, @Param("goodsName") String goodsName, @Param("goodsTypeId") Integer goodsTypeId);

    Long getGoodsCountByGoodsTypeId(Integer goodsTypeId);


    void save(Goods goods);

    void update(@Param("goodsId") Integer goodsId, @Param("goods") Goods goods);

    void deleteById(Integer goodsId);

    List<GoodsVo> getNoInventoryQuantity(@Param("offset") Long offset, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    long getNoInventoryQuantityCount();

    List<GoodsVo> getHasInventoryQuantity(@Param("offset") Long offset, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    long getHasInventoryQuantityCount();

    void saveStock(@Param("goodsId") Integer goodsId, @Param("inventoryQuantity") Integer inventoryQuantity, @Param("purchasingPrice") Double purchasingPrice);

    Goods getGoodsById(Integer goodsId);

    List<GoodsVo> getGoodsListAlarm();

}

