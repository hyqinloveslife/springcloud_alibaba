package com.hyqin.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @description 拦截器配置
 * @author: huangyeqin
 * @create : 2021/6/22  21:03
 */
@Configuration
public class RequestConfig implements WebMvcConfigurer {

    @Resource
    private RequestUrlInterceptor requestUrlInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestUrlInterceptor).addPathPatterns("/sys/**");
    }
}
