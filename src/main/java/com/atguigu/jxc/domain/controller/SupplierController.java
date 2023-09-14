package com.atguigu.jxc.domain.controller;

import com.atguigu.jxc.domain.Pager;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
@RestController
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/supplier/list")
    public Pager<Supplier> getSupplierPage(String supplierName,
                                           Long page,
                                           Integer rows) {
        Pager<Supplier> supplierPager = new Pager<>();
        List<Supplier> supplierList = supplierService.getSupplierPage(supplierName, page, rows);
        supplierPager.setTotal(supplierService.getSupplierCount());
        supplierPager.setRows(supplierList);
        return supplierPager;
    }

    @PostMapping("/supplier/save")
    public ServiceVO saveOrUpdate(Long supplierId,
                                  Supplier supplier) {
        ServiceVO serviceVO = new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        if (supplierId == null) {
            //添加
            supplierService.save(supplier);
        } else {
            //修改
            supplierService.update(supplierId, supplier);
        }

        return serviceVO;
    }

    @PostMapping("/supplier/delete")
    public ServiceVO deleteById(String ids) {
        ServiceVO serviceVO = new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
//        System.out.println("===================" + ids);
        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        supplierService.deleteById(idList);
        return serviceVO;
    }
}
