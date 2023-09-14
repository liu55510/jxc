package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface OverflowListGoodsDao {

    void batchSave(@Param("list") List<OverflowListGoods> overflowListGoodsList);

    List<OverflowListGoods> getOverflowListGoodsList(Integer overflowListId);

}
