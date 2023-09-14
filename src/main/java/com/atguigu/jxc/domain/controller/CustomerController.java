package com.atguigu.jxc.domain.controller;

import com.atguigu.jxc.domain.Pager;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/list")
    public Pager<Customer> getCustomerPage(String customerName,
                                           Long page,
                                           Integer rows) {
        Pager<Customer> customerPager = new Pager<>();
        List<Customer> customerList = customerService.getCustomerPage(customerName, page, rows);
        customerPager.setTotal(customerService.getCustomerCount());
        customerPager.setRows(customerList);
        return customerPager;
    }

    @PostMapping("/customer/save")
    public ServiceVO saveOrUpdate(Long customerId,
                                  Customer customer) {
        ServiceVO serviceVO = new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
        if (customerId == null) {
            //添加
            customerService.save(customer);
        } else {
            //修改
            customerService.update(customerId, customer);
        }

        return serviceVO;
    }

    @PostMapping("/customer/delete")
    public ServiceVO deleteById(String ids) {
        ServiceVO serviceVO = new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
//        System.out.println("===================" + ids);
        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        customerService.deleteById(idList);
        return serviceVO;
    }
}
