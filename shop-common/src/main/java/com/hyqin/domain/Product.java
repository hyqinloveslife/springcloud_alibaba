package com.hyqin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.sql.Clob;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @description 商品信息实体类
 * @author: hyqin
 * @create : 2020/9/5
 */
@ApiModel("商品信息")
@Entity(name = "shop_product")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @ApiModelProperty(value = "商品名称")
    private String pname;

    @ApiModelProperty(value = "商品价格")
    private double pprice;

    @ApiModelProperty(value = "商品库存")
    private Integer stock;

    @ApiModelProperty(value = "商品描述")
    private String descs;

}
