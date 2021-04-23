package com.hyqin.controller;

import cn.dev33.satoken.stp.StpUtil;
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

  @GetMapping("/get")
  public String get(){
    Object loginId = StpUtil.getLoginId();
    System.out.println(loginId);
    return "hello world";
  }

  @GetMapping("/set")
  public String setLogin(){
    // 设置登录id
    StpUtil.setLoginId("root");
    return "设置成功";
  }

}
