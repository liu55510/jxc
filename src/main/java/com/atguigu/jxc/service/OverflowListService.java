package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.OverflowListVo;
import com.atguigu.jxc.entity.OverflowList;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface OverflowListService {
    void saveOverflowList(String overflowNumber, OverflowList overflowList);

    List<OverflowListVo> getOverflowList(String sTime, String eTime);

}
