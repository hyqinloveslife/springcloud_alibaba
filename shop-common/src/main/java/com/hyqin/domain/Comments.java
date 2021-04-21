package com.hyqin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @description 评论实体类
 * @author: hyqin
 * @create : 2020/9/15
 */
@Data
public class Comments {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String content;

    private String author;

    public Comments(String author,String content){
        this.content = content;
        this.author = author;
    }
    public Comments(Long id,String author,String content){
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public Comments(){}
}
