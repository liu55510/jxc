package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface SupplierDao {
    List<Supplier> getSupplierPage(@Param("supplierName") String supplierName, @Param("offset") Long offset, @Param("rows") Integer rows);

    long getSupplierCount();

    void save(Supplier supplier);

    void update(@Param("supplierId") Long supplierId, @Param("supplier") Supplier supplier);

    void deleteById(@Param("ids") List<Integer> ids);
}
