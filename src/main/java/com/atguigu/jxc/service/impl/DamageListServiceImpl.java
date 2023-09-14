package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.DamageListDao;
import com.atguigu.jxc.domain.DamageListVo;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.service.DamageListService;
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
public class DamageListServiceImpl implements DamageListService {
    @Autowired
    private DamageListDao damageListDao;

    @Override
    public void saveDamageList(String damageNumber, DamageList damageList) {
        damageListDao.save(damageNumber, damageList);
    }

    @Override
    public List<DamageListVo> getDamageList(String sTime, String eTime) {
        return damageListDao.getDamageList(sTime, eTime);
    }
}
