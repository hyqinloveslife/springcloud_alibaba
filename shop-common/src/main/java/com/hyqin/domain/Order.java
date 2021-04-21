package com.hyqin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @description 订单信息实体类
 * @author: hyqin
 * @create : 2020/9/5
 */
@Entity(name = "shop_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;
    private Integer uid;
    private Integer pid;

    private String username;
    private String pname;
    private Double pprice;

    private Integer number;  //购买数量
}
