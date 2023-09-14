package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.GoodsTypeService;
import com.atguigu.jxc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private LogService logService;
    @Autowired
    private GoodsTypeDao goodsTypeDao;
    @Autowired
    private GoodsService goodsService;

    @Override
    public ArrayList<Object> loadGoodsType() {
        logService.save(new Log(Log.SELECT_ACTION, "查询商品类别信息"));


        return this.getAllGoodsType(-1); // 根节点默认从-1开始
    }


    @Override
    public void save(String goodsTypeName, Integer pId) {
        GoodsType goodsType = new GoodsType();
        GoodsType parentGoodsType = goodsTypeDao.getGoodsTypeById(pId);
        //如果在本为叶子节点下新增商品类型，需要改为父节点
        if (parentGoodsType.getGoodsTypeState() == 0) {
            parentGoodsType.setGoodsTypeState(1);
            goodsTypeDao.updateGoodsTypeState(parentGoodsType);
        }
        goodsType.setGoodsTypeName(goodsTypeName);
        goodsType.setPId(pId);
        //一开始都是叶子节点
        goodsType.setGoodsTypeState(0);
        goodsTypeDao.save(goodsType);
    }

    @Override
    public ServiceVO<String> deleteById(Integer goodsTypeId) {
        ServiceVO<String> serviceVO = new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        //当前节点的信息
        GoodsType goodsType = goodsTypeDao.getGoodsTypeById(goodsTypeId);
        List<Integer> ids = new ArrayList<>();
        ids.add(goodsTypeId);
        Long parentCount = goodsService.getGoodsCountByGoodsTypeId(goodsTypeId);
        //如果该节点有商品信息，不能删除
        if (parentCount > 0) {
            serviceVO.setCode(ErrorCode.GOODS_TYPE_ERROR_CODE);
            serviceVO.setMsg(ErrorCode.GOODS_TYPE_ERROR_MESS);
            return serviceVO;
        }
        //查询当前节点的父节点的所有子节点
        List<GoodsType> parentOfAllChildGoodsType = goodsTypeDao.getAllGoodsTypeByParentId(goodsType.getPId());
        if (parentOfAllChildGoodsType.size() == 1) {
            //如果父节点的子节点只有一个，那说明就只有当前节点
            //则需要将父节点的状态改为叶子节点
            GoodsType parentGoodsType = goodsTypeDao.getGoodsTypeById(goodsType.getPId());
            parentGoodsType.setGoodsTypeState(0);
            goodsTypeDao.updateGoodsTypeState(parentGoodsType);
        }

        //当前节点的所有子节点
        List<GoodsType> childGoodsType = goodsTypeDao.getAllGoodsTypeByParentId(goodsTypeId);
        //如果不为叶子节点
        if (goodsType.getGoodsTypeState() == 0) {
            List<Long> noGoods = childGoodsType.stream().parallel().map(item -> {
                Integer childGoodsTypeId = item.getGoodsTypeId();
                Long childCount = goodsService.getGoodsCountByGoodsTypeId(childGoodsTypeId);
                return childCount;
            }).filter(item -> item == 0).collect(Collectors.toList());
            if (noGoods.size() != childGoodsType.size()) {
                serviceVO.setCode(50002);
                serviceVO.setMsg("该商品类型的子类型下有商品数据，不能删除！");
                return serviceVO;
            }
        }
        goodsTypeDao.delete(ids);
        return serviceVO;
    }

    /**
     * 递归查询所有商品类别
     *
     * @return
     */
    public ArrayList<Object> getAllGoodsType(Integer parentId) {

        ArrayList<Object> array = this.getGoodSTypeByParentId(parentId);

        for (int i = 0; i < array.size(); i++) {

            HashMap obj = (HashMap) array.get(i);

            if (obj.get("state").equals("open")) {// 如果是叶子节点，不再递归

            } else {// 如果是根节点，继续递归查询
                obj.put("children", this.getAllGoodsType(Integer.parseInt(obj.get("id").toString())));
            }

        }

        return array;
    }

    /**
     * 根据父ID获取所有子商品类别
     *
     * @return
     */
    public ArrayList<Object> getGoodSTypeByParentId(Integer parentId) {

        ArrayList<Object> array = new ArrayList<>();

        List<GoodsType> goodsTypeList = goodsTypeDao.getAllGoodsTypeByParentId(parentId);

        System.out.println("goodsTypeList" + goodsTypeList);
        //遍历商品类别
        for (GoodsType goodsType : goodsTypeList) {

            HashMap obj = new HashMap<String, Object>();

            obj.put("id", goodsType.getGoodsTypeId());
            obj.put("text", goodsType.getGoodsTypeName());

            if (goodsType.getGoodsTypeState() == 1) {
                obj.put("state", "closed");

            } else {
                obj.put("state", "open");
            }

            obj.put("iconCls", "goods-type");

            HashMap<String, Object> attributes = new HashMap<>();
            attributes.put("state", goodsType.getGoodsTypeState());
            obj.put("attributes", attributes);

            array.add(obj);

        }

        return array;
    }
}
