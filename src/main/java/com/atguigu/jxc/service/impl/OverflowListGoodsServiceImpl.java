package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.OverflowListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
@Service
public class OverflowListGoodsServiceImpl implements OverflowListGoodsService {
    @Autowired
    private OverflowListGoodsDao overflowListGoodsDao;

    @Override
    public void saveOverflowListGoods(List<OverflowListGoods> overflowListGoodsList) {
        overflowListGoodsDao.batchSave(overflowListGoodsList);
    }

    @Override
    public List<OverflowListGoods> getOverflowListGoodsList(Integer overflowListId) {
        return overflowListGoodsDao.getOverflowListGoodsList(overflowListId);
    }
}
