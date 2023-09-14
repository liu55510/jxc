package com.atguigu.jxc.domain.controller;

import com.atguigu.jxc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
@RestController
public class UnitController {
    @Autowired
    private UnitService unitService;

    @PostMapping("/unit/list")
    public Map<String, Object> getUnitList() {
        Map<String, Object> rows = new HashMap<>();
        rows.put("rows", unitService.getUnitList());
        return rows;
    }
}
