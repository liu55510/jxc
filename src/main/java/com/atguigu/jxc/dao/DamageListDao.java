package com.atguigu.jxc.dao;

import com.atguigu.jxc.domain.DamageListVo;
import com.atguigu.jxc.entity.DamageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface DamageListDao {

    void save(@Param("damageNumber") String damageNumber, @Param("damageList") DamageList damageList);

    List<DamageListVo> getDamageList(@Param("sTime") String sTime, @Param("eTime") String eTime);

}
