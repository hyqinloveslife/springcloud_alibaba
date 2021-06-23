package com.hyqin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @description 启动类
 * @author: huangyeqin
 * @create : 2021/4/22  11:26
 */
@SpringBootApplication
@MapperScan("com.hyqin.dao")
@ServletComponentScan
public class UcpApplication {

  public static void main(String[] args) {
    SpringApplication.run(UcpApplication.class);
  }
}
