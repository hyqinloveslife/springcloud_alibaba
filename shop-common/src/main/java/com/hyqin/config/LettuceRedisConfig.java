package com.hyqin.config;

import java.io.Serializable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description redis的配置类
 * @author: huangyeqin
 * @create : 2021/1/14  15:19
 */
@EnableCaching  //开启缓存注解
@Configuration
public class LettuceRedisConfig {

  @Bean
  public RedisTemplate<String, Serializable> redisTemplate(
      LettuceConnectionFactory connectionFactory) {
    RedisTemplate<String,Serializable> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setConnectionFactory(connectionFactory);
    return redisTemplate;
  }
}
