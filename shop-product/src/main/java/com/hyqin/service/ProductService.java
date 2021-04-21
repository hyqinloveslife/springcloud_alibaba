package com.hyqin.service;

import com.hyqin.domain.Product;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
public interface ProductService {

    /**
     * 根据商品ID来查询商品信息
     * @param pid
     * @return
     */
    Product findByPid(Integer pid);

    /**
     * @Desc : 保存一个商品信息
     * @Author : huangyeqin
     * @Date : 2020/9/21 20:24
     * @Param : product
     * @Result : com.hyqin.domain.Product
    */
    Product save(Product product);
}
