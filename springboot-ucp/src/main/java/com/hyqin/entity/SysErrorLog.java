package com.hyqin.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_error_log
 * @author 
 */
@Data
public class SysErrorLog implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 序列号
     */
    private String sno;

    /**
     * 服务器IP
     */
    private String serverIp;

    /**
     * trace信息
     */
    private String stackTrace;

    /**
     * 异常名称
     */
    private String exceptionName;

    /**
     * 异常编号
     */
    private String errNo;

    /**
     * 异常消息
     */
    private String errMsg;

    /**
     * url
     */
    private String url;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 是否已删除 0有效，1失效
     */
    private Byte isDeleted;

    /**
     * 状态
     */
    private Byte status;

    private static final long serialVersionUID = 1L;
}