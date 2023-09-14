package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;

import java.util.ArrayList;

/**
 * @description
 */
public interface GoodsTypeService {
    ArrayList<Object> loadGoodsType();

    void save(String goodsTypeName, Integer pId);

    ServiceVO<String> deleteById(Integer goodsTypeId);
}
