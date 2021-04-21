package com.hyqin.vo;

import lombok.Data;

import java.util.List;

/**
 * @description 分页管理
 * @author: hyqin
 * @create : 2020/9/16
 */
@Data
public class PageInfo<T> {
    private int current;
    private int size;
    private Long total;
    private List<T> list;


}
