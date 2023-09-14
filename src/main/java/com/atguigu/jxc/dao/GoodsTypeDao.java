package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description 商品类别
 */
public interface GoodsTypeDao {

    List<GoodsType> getAllGoodsTypeByParentId(Integer pId);

    Integer updateGoodsTypeState(GoodsType parentGoodsType);

    void save(GoodsType goodsType);

    GoodsType getGoodsTypeById(Integer id);

    void delete(@Param("ids") List<Integer> ids);

}
