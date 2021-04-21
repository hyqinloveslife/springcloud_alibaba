package com.hyqin.service;

import com.hyqin.domain.Order;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
public interface OrderService {
    /**
     * 下单
     * @param order
     */
    Order createOrder(Order order);
}
