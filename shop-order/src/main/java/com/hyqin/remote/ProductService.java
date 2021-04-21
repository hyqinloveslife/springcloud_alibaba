package com.hyqin.remote;

import com.hyqin.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description 远程服务本地化
 * @author: hyqin
 * @create : 2020/9/11
 * @version 1.0.0
 */
@FeignClient(value = "service-product")  // value用来指定调用nacos下哪个微服务
public interface ProductService {

    /**
     * 根据pid来获取商品信息（该方法来自商品微服务）
     * @param pid
     * @return product
     */
    @RequestMapping("/product/{pid}")  //指定请求的URI部分
    Product findByPid(@PathVariable("pid") Integer pid);

    @RequestMapping("/product/save")
    Product save(Product product);
}
