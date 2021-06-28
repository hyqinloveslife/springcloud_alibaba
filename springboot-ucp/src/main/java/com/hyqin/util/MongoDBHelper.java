package com.hyqin.util;

import com.hyqin.dto.BaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * @Desc : mongodb的分页查询
     * @Author : huangyeqin
     * @Date : 2021/6/28 19:40
     * @Param : query
     * @param: baseDTO
     * @param: tClass
     * @param: collectionName
     * @Result : java.util.Map<java.lang.String,java.lang.Object>
     **/
    public Map<String, Object> findByPage(Query query, BaseDTO baseDTO, Class tClass, String collectionName) {
        Map<String, Object> resultMap = new HashMap<>();
        int currentPage = baseDTO.getPageNum();
        int pageSize = baseDTO.getPageSize();

        //Pageable pageable = PageRequest.of(baseDTO.getPageNum(),baseDTO.getPageSize());
        //query.with(pageable);
        // 设置起始页数 和分页查询数
        query.skip((currentPage - 1) * pageSize)
                // 设置查询条数
                .limit(pageSize);


        // 查询记录总数
        System.out.println("查询记录总数:" + mongoTemplate.count(new Query(), collectionName));
        int totalCount = (int) mongoTemplate.count(new Query(), tClass, collectionName);

        // 数据总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

        resultMap.put("total", totalCount);
        resultMap.put("pages", totalPage);
        resultMap.put("current", baseDTO.getPageNum());
        resultMap.put("size", baseDTO.getPageSize());
        List list = mongoTemplate.find(query, tClass, collectionName);
        System.out.println("当前记录总数：{}" + list.size());
        //List list = mongoTemplate.findAll( tClass, collectionName);
        resultMap.put("records", list);
        return resultMap;
    }

}
