package com.hyqin.es.entity;

import java.io.Serializable;
import java.sql.Clob;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Component;

/**
 * @description 商品模块
 * @author: huangyeqin
 * @create : 2021/1/18  11:48
 */
@Component
@Document(indexName = "shop-product", type = "product")
@Data
public class ProductEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;

  /**
   * 商品名称
   */
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String name;

  /**
   * 商品摘要
   */
  private String summary;

  /**
   * 商品简介
   */
  @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
  private String descs;

  /**
   * 商品价格
   */
  private Double price;

  /**
   * 商品产地
   */
  private String address;

  /**
   * 消息，测试一个富文本问题
   */
  private Clob message;
}
