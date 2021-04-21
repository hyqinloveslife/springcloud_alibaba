package com.hyqin.controller;

import com.alibaba.fastjson.JSON;
import com.hyqin.domain.Order;
import com.hyqin.domain.Product;
import com.hyqin.remote.ProductService;
import com.hyqin.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description 测试sentinel
 * @author: hyqin
 * @create : 2020/9/5
 */
@RestController
@Slf4j
public class OrderController2 {
    @Autowired
    private ProductService productService;

    @RequestMapping("/order/message")
    public String message(){
        return "hello 高并发";
    }

    @RequestMapping("/order/message1")
    public String message1(){
        return "hello 高并发2";
    }

    @RequestMapping(value = "/order/saveprod")
    public String save(){
        Product product = new Product();
        product.setPid(100);
        product.setPname("元气森林");
        product.setPprice(18.2);
        product.setStock(1);
        log.info("保存的商品信息为 ： {}",product.toString());
        Product p = productService.save(product);
        return "success";
    }

}
