package com.hyqin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/4/22  15:08
 */
@Data
@TableName("sys_role")
public class PermissionDO {

  @TableId(value = "id")
  private Integer id;

  @TableField(value = "name", updateStrategy = FieldStrategy.NOT_EMPTY)
  private String name;

  @TableField(value = "desc", updateStrategy = FieldStrategy.NOT_EMPTY)
  private String desc;
}
