package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.OverflowListGoods;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface OverflowListGoodsService {
    void saveOverflowListGoods(List<OverflowListGoods> overflowListGoodsList);

    List<OverflowListGoods> getOverflowListGoodsList(Integer overflowListId);
}
