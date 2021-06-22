package com.hyqin.dto;

import lombok.Data;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/22  16:14
 */
@Data
public class BaseDTO {
    /** 页索引 */
    private int pageNum;

    /** 每页数量 */
    private int pageSize;
}
