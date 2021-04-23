package com.hyqin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/4/22  14:05
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  

  /**
   * 设置表单登录等选项
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/4/22 14:06
   * @param : http
   * @result : void
  */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

  }
}
