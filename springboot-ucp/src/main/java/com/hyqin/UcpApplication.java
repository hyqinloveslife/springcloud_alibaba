package com.hyqin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description 启动类
 * @author: huangyeqin
 * @create : 2021/4/22  11:26
 */
@SpringBootApplication
@MapperScan("com.hyqin.dao")
public class UcpApplication {

  public static void main(String[] args) {
    SpringApplication.run(UcpApplication.class);
  }
}
