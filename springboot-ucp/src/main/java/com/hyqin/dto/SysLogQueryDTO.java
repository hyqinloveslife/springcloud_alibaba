package com.hyqin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/22  19:35
 */
@Data
public class SysLogQueryDTO extends BaseDTO {
    /**
     * 创建时间
     */
    private Date createTimeStart;

    /**
     * 创建-结束时间
     */
    private Date createTimeEnd;

    /**
     * 操作人id
     */
    private String createdBy;

    /**
     * 方法 post或者get
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 用户姓名
     */
    private String username;
}
