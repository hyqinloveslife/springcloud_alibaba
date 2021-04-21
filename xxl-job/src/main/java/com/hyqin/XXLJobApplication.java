package com.hyqin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description 启动类
 * @author: huangyeqin
 * @create : 2020/9/19  15:28
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class XXLJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(XXLJobApplication.class);
    }
}
