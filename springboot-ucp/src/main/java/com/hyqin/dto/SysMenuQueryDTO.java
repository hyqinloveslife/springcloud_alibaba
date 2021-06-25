package com.hyqin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/22  19:09
 */
@Data
public class SysMenuQueryDTO extends BaseDTO {
    /**
     * 创建时间
     */
    private Date createTimeStart;

    /**
     * 创建-结束时间
     */
    private Date createTimeEnd;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 菜单id
     */
    private Long id;

    /**
     * 菜单类型
     */
    private String type;
}
