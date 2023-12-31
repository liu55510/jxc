package com.atguigu.jxc.domain.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.service.GoodsTypeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 商品类别控制器
 */
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;


    /**
     * 查询所有商品类别
     *
     * @return easyui要求的JSON格式字符串
     */
    @PostMapping("/loadGoodsType")
    @RequiresPermissions(value = {"商品管理", "进货入库", "退货出库", "销售出库", "客户退货", "当前库存查询", "商品报损", "商品报溢", "商品采购统计"}, logical = Logical.OR)
    public ArrayList<Object> loadGoodsType() {
        return goodsTypeService.loadGoodsType();
    }

    @PostMapping("/save")
    public ServiceVO save(String goodsTypeName,
                          Integer pId) {
        ServiceVO serviceVO = new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        goodsTypeService.save(goodsTypeName,pId);
        return serviceVO;
    }

    @PostMapping("/delete")
    public ServiceVO<String> deleteById(Integer goodsTypeId) {
        ServiceVO<String> serviceVO = goodsTypeService.deleteById(goodsTypeId);
        return serviceVO;
    }
}
