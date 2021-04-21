package com.hyqin;

import cn.dev33.satoken.SaTokenManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description springboot的启动类
 * @author: hyqin
 * @create : 2020/9/5
 */
@SpringBootApplication
public class UserApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class);
    System.out.println("启动成功：sa-token配置如下：" + SaTokenManager.getConfig());
  }
}
