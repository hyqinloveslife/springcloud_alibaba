package com.hyqin.repository;

import com.hyqin.es.entity.ProductEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.lang.Long;

/**
 * @description 商品管理的elasticsearch
 * @author: huangyeqin
 * @create : 2021/1/18  11:46
 */
@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductEntity, Long> {

  /**
   * 根据商品的描述来查询
   * 这种查询没办法分词
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/18 21:00
   * @Param : keywords
   * @Result : java.util.List<com.hyqin.es.entity.ProductEntity>
  */
  List<ProductEntity> findByDescs(String keywords);

  /**
   * 根据id来查询商品信息详情
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/19 9:06
   * @Param : aLong
   * @Result : java.util.Optional<com.hyqin.es.entity.ProductEntity>
  */
  @Override
  Optional<ProductEntity> findById(Long aLong);
}
