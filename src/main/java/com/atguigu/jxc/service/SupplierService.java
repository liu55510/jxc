package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Supplier;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface SupplierService {
    List<Supplier> getSupplierPage(String supplierName, Long page, Integer rows);

    long getSupplierCount();

    void save(Supplier supplier);

    void update(Long supplierId, Supplier supplier);

    void deleteById(List<Integer> ids);
}
