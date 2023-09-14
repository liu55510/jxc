package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface DamageListGoodsDao {
    void batchSave(@Param("list") List<DamageListGoods> damageListGoodsList);

    List<DamageListGoods> getDamageListGoodsList(Integer damageListId);
}
