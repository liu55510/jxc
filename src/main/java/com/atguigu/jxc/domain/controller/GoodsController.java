package com.atguigu.jxc.domain.controller;

import com.atguigu.jxc.domain.GoodsVo;
import com.atguigu.jxc.domain.Pager;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import com.google.gson.Gson;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 商品信息Controller
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询商品库存信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param codeOrName  商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("/goods/listInventory")
    public Pager<GoodsVo> getGoodsStockPage(Long page,
                                            Integer rows,
                                            String codeOrName,
                                            Integer goodsTypeId) {
        Pager<GoodsVo> pager = new Pager<>();
        pager.setPage(page);
        pager.setSize(rows);
        List<GoodsVo> goodsVos = goodsService.getGoodsStockPage(page, rows, codeOrName, goodsTypeId);
        pager.setRows(goodsVos);
        pager.setTotal(goodsService.getGoodsTotal());

        return pager;
    }

    /**
     * 分页查询商品信息
     *
     * @param page        当前页
     * @param rows        每页显示条数
     * @param goodsName   商品名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("/goods/list")
    public Pager<GoodsVo> getGoodsPage(Long page,
                                       Integer rows,
                                       String goodsName,
                                       Integer goodsTypeId) {
        Pager<GoodsVo> pager = new Pager<>();
        pager.setPage(page);
        pager.setSize(rows);
        if (goodsTypeId != null && goodsTypeId == 1) {
            goodsTypeId = null;
        }
        List<GoodsVo> goodsVos = goodsService.getGoodsPage(page, rows, goodsName, goodsTypeId);
        pager.setRows(goodsVos);
        pager.setTotal(goodsService.getGoodsTotal());

        return pager;
    }


    /**
     * 生成商品编码
     *
     * @return
     */
    @RequestMapping("/getCode")
    @RequiresPermissions(value = "商品管理")
    public ServiceVO getCode() {
        return goodsService.getCode();
    }

    /**
     * 添加或修改商品信息
     *
     * @param goods 商品信息实体
     * @return
     */
    @PostMapping("/goods/save")
    public ServiceVO saveOrUpdate(Integer goodsId,
                                  Goods goods) {
        ServiceVO vo = new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        if (goodsId == null) {
            //新增
            goodsService.save(goods);
        } else {
            //修改
            goodsService.update(goodsId, goods);
        }
        return vo;
    }

    /**
     * 删除商品信息
     *
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("/goods/delete")
    public ServiceVO deleteById(Integer goodsId) {
        ServiceVO vo = new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        goodsService.deleteById(goodsId);
        return vo;
    }

    /**
     * 分页查询无库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("/goods/getHasInventoryQuantity")
    public Pager<GoodsVo> getHasInventoryQuantity(Long page,
                                                  Integer rows,
                                                  String nameOrCode) {
        Pager<GoodsVo> pager = new Pager<>();
        List<GoodsVo> goodsVos = goodsService.getHasInventoryQuantity(page, rows, nameOrCode);
        pager.setRows(goodsVos);
        pager.setTotal(goodsService.getHasInventoryQuantityCount());
        return pager;
    }

    /**
     * 分页查询有库存商品信息
     *
     * @param page       当前页
     * @param rows       每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("/goods/getNoInventoryQuantity")
    public Pager<GoodsVo> getNoInventoryQuantity(Long page,
                                                 Integer rows,
                                                 String nameOrCode) {
        Pager<GoodsVo> pager = new Pager<>();
        List<GoodsVo> goodsVos = goodsService.getNoInventoryQuantity(page, rows, nameOrCode);
        pager.setRows(goodsVos);
        pager.setTotal(goodsService.getNoInventoryQuantityCount());
        return pager;
    }


    /**
     * 添加商品期初库存
     *
     * @param goodsId           商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice   成本价
     * @return
     */
    @PostMapping("/goods/saveStock")
    public ServiceVO saveStock(Integer goodsId,
                               Integer inventoryQuantity,
                               Double purchasingPrice) {
        ServiceVO vo = new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        goodsService.saveStock(goodsId, inventoryQuantity, purchasingPrice);
        return vo;
    }

    /**
     * 删除商品库存
     *
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("/goods/deleteStock")
    public ServiceVO deleteStock(Integer goodsId) {
        ServiceVO vo = goodsService.deleteStock(goodsId);
        return vo;
    }

    /**
     * 查询库存报警商品信息
     *
     * @return
     */
    @PostMapping("/goods/listAlarm")
    public Map<String, List<GoodsVo>> getGoodsListAlarm() {
        Map<String, List<GoodsVo>> map = new HashMap<>();
        List<GoodsVo> rows = goodsService.getGoodsListAlarm();
        map.put("rows", rows);
        return map;
    }


}
