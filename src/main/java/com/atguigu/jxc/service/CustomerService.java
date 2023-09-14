package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Customer;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface CustomerService {
    List<Customer> getCustomerPage(String customerName, Long page, Integer rows);

    long getCustomerCount();

    void save(Customer customer);

    void update(Long customerId, Customer customer);

    void deleteById(List<Integer> ids);
}
