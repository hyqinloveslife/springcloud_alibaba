package com.hyqin.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @description 用户信息实体类
 * @author: hyqin
 * @create : 2020/9/5
 */
@Data
@TableName("sys_user")
public class UserDO implements Serializable {

  @TableId(value = "id")
  private Integer uid;

  @TableField(value = "username", updateStrategy = FieldStrategy.NOT_EMPTY)
  private String account;

  @TableField(value = "username", updateStrategy = FieldStrategy.NOT_EMPTY)
  private String username;

  @TableField(value = "password",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String password;

  @TableField(value = "status",updateStrategy = FieldStrategy.NOT_EMPTY)
  private String status;

}
