package com.atguigu.jxc.dao;

import com.atguigu.jxc.domain.OverflowListVo;
import com.atguigu.jxc.entity.OverflowList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface OverflowListDao {
    void save(@Param("overflowNumber") String overflowNumber, @Param("overflowList") OverflowList overflowList);


    List<OverflowListVo> getOverflowList(@Param("sTime") String sTime, @Param("eTime") String eTime);

}
