package com.hyqin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description 启动类
 * @author: hyqin
 * @create : 2020/9/5
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductApplication {

  public static void main(String[] args) {
    System.setProperty("es.set.netty.runtime.available.processors", "false");
    SpringApplication.run(ProductApplication.class);
  }
}
