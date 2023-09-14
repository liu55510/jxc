package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerDao;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getCustomerPage(String customerName, Long page, Integer rows) {
        page = page == 0 ? 1 : page;
        Long offset = (page - 1) * rows;
        List<Customer> customerPage = customerDao.getCustomerPage(customerName, offset, rows);

        return customerPage;
    }

    @Override
    public long getCustomerCount() {
        return customerDao.getCustomerCount();
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void update(Long customerId, Customer customer) {
        customerDao.update(customerId, customer);
    }

    @Override
    public void deleteById(List<Integer> ids) {
        customerDao.deleteById(ids);
    }
}
