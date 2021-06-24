package com.hyqin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description mongodb操作工具类
 * @author: huangyeqin
 * @create : 2021/6/23  16:25
 */
@Component
public class MongoDBHelper {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @Desc : 保存
     * @Author : huangyeqin
     * @Date : 2021/6/23 16:26
     * @Param : t
     * @Result : T
     **/
    public <T> T save(T t) {
        return mongoTemplate.save(t);
    }

    /**
     * @Desc : 保存到指定的collection
     * @Author : huangyeqin
     * @Date : 2021/6/23 16:27
     * @Param : t
     * @param: collectionName
     * @Result : T
     **/
    public <T> T save(T t, String collectionName) {
        return mongoTemplate.save(t, collectionName);
    }

    /**
     * @Desc : 查询
     * @Author : huangyeqin
     * @Date : 2021/6/23 16:29
     * @Param : query
     * @param: tClass
     * @Result : java.util.List<T>
     **/
    public <T> List<T> find(Query query, Class<T> tClass, String collectionName) {
        return mongoTemplate.find(query, tClass, collectionName);
    }

    /**
     * @Desc : 查询所有
     * @Author : huangyeqin
     * @Date : 2021/6/23 16:29
     * @Param : tClass
     * @Result : java.util.List<T>
     **/
    public <T> List<T> findAll(Class<T> tClass) {
        return mongoTemplate.findAll(tClass);
    }

    /**
     * @Desc : 查询所有
     * @Author : huangyeqin
     * @Date : 2021/6/23 16:30
     * @Param : tClass
     * @param: collectionName
     * @Result : java.util.List<T>
     **/
    public <T> List<T> findAll(Class<T> tClass, String collectionName) {
        return mongoTemplate.findAll(tClass, collectionName);
    }
}
