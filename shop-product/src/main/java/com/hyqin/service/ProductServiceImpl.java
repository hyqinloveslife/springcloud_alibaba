package com.hyqin.service;

import com.hyqin.dao.ProductDao;
import com.hyqin.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Cacheable(value = "product",key = "#pid")
    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }
}
