package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.service.DamageListGoodsService;
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
public class DamageListGoodsServiceImpl implements DamageListGoodsService {
    @Autowired
    private DamageListGoodsDao damageListGoodsDao;

    @Override
    public void saveDamageListGoods(List<DamageListGoods> damageListGoodsList) {
        damageListGoodsDao.batchSave(damageListGoodsList);
    }

    @Override
    public List<DamageListGoods> getDamageListGoodsList(Integer damageListId) {
        return damageListGoodsDao.getDamageListGoodsList(damageListId);
    }
}
