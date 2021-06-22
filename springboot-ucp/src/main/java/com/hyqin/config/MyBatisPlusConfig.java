package com.hyqin.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description mybatis-plus的插件配置
 * @author: huangyeqin
 * @create : 2021/6/22  16:47
 */

@Configuration
@EntityScan("com.hyqin.entity")
@MapperScan(basePackages = "com.hyqin.dao",markerInterface = BaseMapper.class)
public class MyBatisPlusConfig {
    /**
     * 插件集合
     */

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        // 插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);

        // 添加分页插件
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return  interceptor;
    }
}
