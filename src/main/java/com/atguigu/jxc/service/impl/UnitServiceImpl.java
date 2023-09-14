package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.UnitDao;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitDao unitDao;
    @Override
    public List<Unit> getUnitList() {
        return unitDao.getUnitList();
    }
}
