package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * title：
 * author:liuchun
 * date:2023/9/11
 * description:
 */
public interface CustomerDao {
    List<Customer> getCustomerPage(@Param("customerName") String customerName, @Param("offset") Long offset, @Param("rows") Integer rows);

    long getCustomerCount();

    void save(Customer customer);

    void update(@Param("customerId") Long customerId, @Param("customer") Customer customer);

    void deleteById(@Param("ids") List<Integer> ids);
}
