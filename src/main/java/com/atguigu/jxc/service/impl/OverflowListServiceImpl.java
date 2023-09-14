package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.OverflowListDao;
import com.atguigu.jxc.domain.OverflowListVo;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.service.OverflowListService;
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
public class OverflowListServiceImpl implements OverflowListService {
    @Autowired
    private OverflowListDao overflowListDao;

    @Override
    public void saveOverflowList(String overflowNumber, OverflowList overflowList) {
        overflowListDao.save(overflowNumber, overflowList);
    }

    @Override
    public List<OverflowListVo> getOverflowList(String sTime, String eTime) {
        return overflowListDao.getOverflowList(sTime, eTime);
    }
}
