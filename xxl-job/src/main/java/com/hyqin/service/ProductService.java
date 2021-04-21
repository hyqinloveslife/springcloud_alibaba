package com.hyqin.service;

import com.hyqin.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description 通过rpc调用远程服务
 * @author: huangyeqin
 * @create : 2020/9/21  20:24
 */
@FeignClient(value = "service-product")
public interface ProductService {

    @RequestMapping("/product/save")
    Product save(Product product);

}
