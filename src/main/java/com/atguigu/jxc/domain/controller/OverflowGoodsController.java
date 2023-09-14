package com.atguigu.jxc.domain.controller;

import com.atguigu.jxc.domain.DamageListVo;
import com.atguigu.jxc.domain.OverflowListVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.OverflowListGoodsService;
import com.atguigu.jxc.service.OverflowListService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
@RestController
public class OverflowGoodsController {
    @Autowired
    private OverflowListService overflowListService;
    @Autowired
    private OverflowListGoodsService overflowListGoodsService;

    @PostMapping("/overflowListGoods/save")
    public ServiceVO saveOverflowListGoods(OverflowList overflowList,
                                           String overflowListGoodsStr,
                                           String overflowNumber,
                                           HttpServletRequest request) {
        ServiceVO serviceVO = new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        Gson gson = new Gson();
        Type type = new TypeToken<List<OverflowListGoods>>() {
        }.getType();
        List<OverflowListGoods> overflowListGoodsList = gson.fromJson(overflowListGoodsStr, type);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        overflowList.setUserId(user.getUserId());
        overflowListService.saveOverflowList(overflowNumber, overflowList);
        Integer overflowListId = overflowList.getOverflowListId();
        overflowListGoodsList.stream().parallel().forEach(item -> {
            item.setOverflowListId(overflowListId);
        });
        overflowListGoodsService.saveOverflowListGoods(overflowListGoodsList);
        return serviceVO;
    }

    @PostMapping("/overflowListGoods/list")
    public Map<String, Object> getDamageList(String sTime,
                                             String eTime) {
        Map<String, Object> map = new HashMap<>();
        List<OverflowListVo> overflowListVos = overflowListService.getOverflowList(sTime, eTime);
        map.put("rows", overflowListVos);
        return map;
    }

    @PostMapping("/overflowListGoods/goodsList")
    public Map<String, Object> getDamageListGoodsList(Integer overflowListId) {
        Map<String, Object> map = new HashMap<>();
        List<OverflowListGoods> overflowListGoodsList = overflowListGoodsService.getOverflowListGoodsList(overflowListId);
        map.put("rows", overflowListGoodsList);
        return map;
    }
}
