package com.hyqin.controller;

import cn.dev33.satoken.dao.SaTokenDaoRedis;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
@RestController
@RequestMapping("/base")
public class UserController {

  @Qualifier("saTokenDaoRedis")
  @Autowired
  SaTokenDaoRedis redisClient;

  @GetMapping("/get")
  public String get(){
    Object loginId = StpUtil.getLoginId();
    System.out.println(loginId);
//    System.out.println("是否已经登录："+StpUtil.isLogin());

    return "hello world";
  }

  @GetMapping("/set")
  public String setLogin(){
    // 设置登录id
    StpUtil.setLoginId("root");

    redisClient.setValue("user","root", -1);

    return "设置成功";
  }

}
