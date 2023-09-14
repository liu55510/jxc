package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageListGoods;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface DamageListGoodsService {
    void saveDamageListGoods(List<DamageListGoods> damageListGoodsList);

    List<DamageListGoods> getDamageListGoodsList(Integer damageListId);
}
