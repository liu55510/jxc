package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    @Override
    public List<Supplier> getSupplierPage(String supplierName, Long page, Integer rows) {
        page = page == 0 ? 1 : page;
        Long offset = (page - 1) * rows;
        List<Supplier> supplierPage = supplierDao.getSupplierPage(supplierName, offset, rows);

        return supplierPage;
    }

    @Override
    public long getSupplierCount() {
        return supplierDao.getSupplierCount();
    }

    @Override
    public void save(Supplier supplier) {
        supplierDao.save(supplier);
    }

    @Override
    public void update(Long supplierId, Supplier supplier) {
        supplierDao.update(supplierId,supplier);
    }

    @Override
    public void deleteById(List<Integer> ids) {
        supplierDao.deleteById(ids);
    }
}
