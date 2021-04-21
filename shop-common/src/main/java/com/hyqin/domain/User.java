package com.hyqin.domain;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @description 用户信息实体类
 * @author: hyqin
 * @create : 2020/9/5
 */
@Entity(name = "shop_user")   //实体类跟数据库表的对应
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private String account;
    private String username;
    private String password;
    private String telephone;

}
