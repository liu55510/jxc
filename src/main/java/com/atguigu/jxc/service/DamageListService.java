package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.DamageListVo;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface DamageListService {
    void saveDamageList(String damageNumber, DamageList damageList);

    List<DamageListVo> getDamageList(String sTime, String eTime);


}
