package com.hyqin.service;

import com.hyqin.dao.OrderDao;
import com.hyqin.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order createOrder(Order order) {
        return orderDao.save(order);
    }
}
