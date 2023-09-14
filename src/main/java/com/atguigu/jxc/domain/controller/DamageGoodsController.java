package com.atguigu.jxc.domain.controller;

import com.atguigu.jxc.domain.DamageListVo;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.DamageListGoodsService;
import com.atguigu.jxc.service.DamageListService;
import com.fasterxml.jackson.core.type.TypeReference;
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
public class DamageGoodsController {
    @Autowired
    private DamageListService damageListService;
    @Autowired
    private DamageListGoodsService damageListGoodsService;

    @PostMapping("/damageListGoods/save")
    public ServiceVO saveDamageListGoods(DamageList damageList,
                                         String damageListGoodsStr,
                                         String damageNumber,
                                         HttpServletRequest request) {
        ServiceVO serviceVO = new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        Gson gson = new Gson();
        Type type = new TypeToken<List<DamageListGoods>>() {
        }.getType();
        List<DamageListGoods> damageListGoodsList = gson.fromJson(damageListGoodsStr, type);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        damageList.setUserId(user.getUserId());
        damageListService.saveDamageList(damageNumber, damageList);
        Integer damageListId = damageList.getDamageListId();
        damageListGoodsList.stream().parallel().forEach(item -> {
            item.setDamageListId(damageListId);
        });
        damageListGoodsService.saveDamageListGoods(damageListGoodsList);
        return serviceVO;
    }

    @PostMapping("/damageListGoods/list")
    public Map<String, Object> getDamageList(String sTime,
                                                      String eTime) {
        Map<String, Object> map = new HashMap<>();
        List<DamageListVo> damageLists = damageListService.getDamageList(sTime,eTime);
        map.put("rows",damageLists);
        return map;
    }

    @PostMapping("/damageListGoods/goodsList")
    public Map<String, Object> getDamageListGoodsList(Integer damageListId) {
        Map<String, Object> map = new HashMap<>();
        List<DamageListGoods> damageListGoodsList = damageListGoodsService.getDamageListGoodsList(damageListId);
        map.put("rows",damageListGoodsList);
        return map;
    }

}
