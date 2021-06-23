package com.hyqin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/23  10:18
 */
@Data
public class SysErrorLogQueryDTO extends BaseDTO {
    /**
     * 创建时间
     */
    private Date createTimeStart;

    /**
     * 创建-结束时间
     */
    private Date createTimeEnd;

    /**
     * 序列号
     */
    private String sno;

    /**
     * 异常编号
     */
    private String errNo;
}
