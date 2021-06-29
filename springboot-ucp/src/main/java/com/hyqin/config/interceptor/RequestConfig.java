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
        // 拦截规则  所有的系统管理，但是不拦截查询操作日志
        registry.addInterceptor(requestUrlInterceptor).addPathPatterns("/sys/**")
                .excludePathPatterns("/sys/log/list-log-page")
                .excludePathPatterns("/sys/user/info")
                .excludePathPatterns("/sys/log/list-error-log");
    }
}
