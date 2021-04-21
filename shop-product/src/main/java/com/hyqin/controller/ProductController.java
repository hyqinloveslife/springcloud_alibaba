package com.hyqin.controller;

import com.alibaba.fastjson.JSON;
import com.hyqin.config.Api_Business;
import com.hyqin.domain.Product;
import com.hyqin.repository.ProductRepository;
import com.hyqin.dto.BaseQueryDTO;
import com.hyqin.es.entity.ProductEntity;
import com.hyqin.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
@Api_Business
@Api(value = "商品管理", tags = "商品管理-api")
@RestController
@Slf4j
@RequestMapping("/web")
public class ProductController {

  @Autowired
  private ProductService productService;
  @Autowired
  private ProductRepository productRepository;

  @ApiOperation("根据id查询商品信息")
  @GetMapping("/product/{pid}")
  public Product product(@PathVariable("pid") Integer pid) {
    log.info("接下來要進行{}号商品信息查询", pid);

    Product product = productService.findByPid(pid);

    log.info("商品信息查询成功,内容为{}", JSON.toJSONString(product));

    return product;
  }

  @ApiOperation(value = "保存商品信息")
  @PostMapping("/product/save")
  public Product saveProd(@RequestBody Product product) {
    log.info("将商品{}写入数据库",product);
    Product productReturn = productService.save(product);
    log.info("写入商品信息成功，返回结果 {}",productReturn);

    log.info("将商品信息写入elastic search");
    ProductEntity productEntity = new ProductEntity();
    productEntity.setId(productReturn.getPid());
    productEntity.setName(productReturn.getPname());
    productEntity.setDescs(productReturn.getDescs());
    productEntity.setPrice(productReturn.getPprice());
    ProductEntity entity = productRepository.save(productEntity);
    log.info("写入es成功{}",entity);

    return productReturn;
  }

  @ApiOperation(value = "通过es进行模糊查询")
  @GetMapping("/query-product")
  public String query(@RequestBody BaseQueryDTO baseQueryDTO) {
    NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
    searchQuery.withQuery(QueryBuilders.matchQuery("descs",baseQueryDTO.getKeywords()));
    Page<ProductEntity> search = productRepository.search(searchQuery.build());
    log.info("开始在es库中查询与{}相关的内容",baseQueryDTO.getKeywords());
//    List<ProductEntity> search = productRepository.findByDescs(baseQueryDTO.getKeywords());
    log.info("从es库中查询出的结果{}",JSON.toJSONString(search));
    return JSON.toJSONString(search);
  }


  @ApiOperation(value = "分页查询所有")
  @GetMapping("/list-product")
  public String listAll(@RequestBody BaseQueryDTO baseQueryDTO){
    NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
    searchQuery.withQuery(QueryBuilders.matchAllQuery());
    Page<ProductEntity> search = productRepository.search(searchQuery.build());
    return JSON.toJSONString(search);
  }

}
