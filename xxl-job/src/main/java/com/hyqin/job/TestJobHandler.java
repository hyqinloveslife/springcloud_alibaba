package com.hyqin.job;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.hyqin.domain.Product;
import com.hyqin.service.ProductService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description 测试一下
 * @author: huangyeqin
 * @create : 2020/9/19  15:32
 */
@Component
public class TestJobHandler {
    private static Logger logger = LoggerFactory.getLogger(TestJobHandler.class);

    @Autowired
    private ProductService productService;

    @XxlJob(value = "testJobHandler1")
    public ReturnT<String> demoJobHandler(String param){
        logger.info("测试xxl-job {}",param);

//        Product product = new Product();
//        product.setPname(param);
//        product.setPprice(18.2);
//        product.setStock(1);
//        logger.info("保存的商品信息为 ： {}",product.toString());
//        Product p = productService.save(product);
        return ReturnT.SUCCESS;
    }


}
