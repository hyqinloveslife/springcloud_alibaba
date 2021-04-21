package com.hyqin.dto;

import io.swagger.annotations.ApiModel;
import java.sql.Clob;
import lombok.Data;

/**
 * @description 通用查询dto
 * @author: huangyeqin
 * @create : 2021/1/18  19:54
 */
@ApiModel(value = "通用查询dto")
@Data
public class BaseQueryDTO {
  private String keywords;

  private Integer pageNo;

  private Integer pageSize;

}
