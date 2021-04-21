package com.hyqin.controller;

import com.alibaba.fastjson.JSON;
import com.hyqin.domain.Order;
import com.hyqin.domain.Product;
import com.hyqin.remote.ProductService;
import com.hyqin.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
//@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductService productService;

//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接收到{}号商品下单请求，开始调用商品微服务查询此商品信息",pid);
//
//        /**
//         * 1、将地址URL固定，有一个缺点，就是当服务提供者的地址发生变化，就必须同步修改服务调用者的代码
//         * 2、当端口号固定时，无法适应集群。
//         */
//        Product product = restTemplate.getForObject("http://localhost:8081/product/"+pid,Product.class);
//
//        log.info("查询到的商品信息{}"+ JSON.toJSONString(product));
//
//        Order order = new Order();
//        order.setNumber(1);
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//
//        order.setUid(1);
//        order.setUsername("李白");
//
//        orderService.createOrder(order);
//
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }

    /**
     * 使用了nacos后的写法
     * @param pid
     * @return
     */
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接收到{}号商品下单请求，开始调用商品微服务查询此商品信息",pid);
//
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//
//        ServiceInstance serviceInstance = instances.get(0);
//
//        Product product =
//                restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/"+pid,Product.class);
//
//        log.info("查询到的商品信息{}"+ JSON.toJSONString(product));
//
//        Order order = new Order();
//        order.setNumber(1);
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//
//        order.setUid(1);
//        order.setUsername("李白");
//
//        orderService.createOrder(order);
//
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }

//    /**
//     * 使用ribbon負載均衡的写法
//     * @param pid
//     * @return
//     */
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接收到{}号商品下单请求，开始调用商品微服务查询此商品信息",pid);
//
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//
//        ServiceInstance serviceInstance = instances.get(0);
//
//        Product product =
//                restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/"+pid,Product.class);
//
//        log.info("查询到的商品信息{}"+ JSON.toJSONString(product));
//
//        Order order = new Order();
//        order.setNumber(1);
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//
//        order.setUid(1);
//        order.setUsername("李白");
//
//        orderService.createOrder(order);
//
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }

    /**
     * 使用ribbon負載均衡的写法
     * 默认的负载均衡策略是轮询
     * 在resttemplate注册的地方加上@loadBalance的注解
     * @param pid
     * @return
     */
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("接收到{}号商品下单请求，开始调用商品微服务查询此商品信息",pid);
//
//        /**
//         * 1、代码可读性不好
//         * 2、编码风格不统一
//         */
//        Product product =  restTemplate.getForObject("http://service-product/product/"+pid,Product.class);
//
//        log.info("查询到的商品信息{}"+ JSON.toJSONString(product));
//
//        Order order = new Order();
//        order.setNumber(1);
//        order.setPid(product.getPid());
//        order.setPname(product.getPname());
//        order.setPprice(product.getPprice());
//
//        order.setUid(1);
//        order.setUsername("李白");
//
////        orderService.createOrder(order);
////
////        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
//        return order;
//    }

    /**
     * 使用feign来实现服务调用<br/>
     * feign默认集成了ribbon
     * @param pid
     * @return
     */
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接收到{}号商品下单请求，开始调用商品微服务查询此商品信息",pid);

        // 调用微服务接口像调用本地接口一样
        Product product = productService.findByPid(pid);

        log.info("查询到的商品信息{}"+ JSON.toJSONString(product));

        Order order = new Order();
        order.setNumber(1);
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());

        order.setUid(1);
        order.setUsername("李白");

//        orderService.createOrder(order);
//
//        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        return order;
    }
}
