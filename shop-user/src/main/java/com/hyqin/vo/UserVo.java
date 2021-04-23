package com.hyqin.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/4/22  15:58
 */
@Data
public class UserVo implements Serializable {

  private static final long serialVersionUID = -7625206215004743668L;

  private String token;

  private String account;

  private String password;
}
