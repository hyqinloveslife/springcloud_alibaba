package com.hyqin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/1/18  18:03
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

  @Value(value = "true")
  private boolean enable;

  /**
   * 配置swagger2核心配置docket
   *
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/1/6 14:43
   * @Param : null
   * @Result :
   */
  @Bean
  public Docket restApiBusiness() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("业务分组接口")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api_Business.class))
        .paths(PathSelectors.any())
        .build()
        .enable(enable);
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("业务分组接口")
        .contact(new Contact("黄叶钦", "", "hyqin930804@163.com"))
        .description("ifknow测试Swagger")
        .version("1.0.0")
        .termsOfServiceUrl("")
        .build();
  }
}
