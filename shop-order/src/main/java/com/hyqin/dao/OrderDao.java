package com.hyqin.dao;

import com.hyqin.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
public interface OrderDao extends JpaRepository<Order,Integer> {
}
