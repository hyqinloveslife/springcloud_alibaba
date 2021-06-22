package com.hyqin.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_user_token
 * @author 
 */
@Data
public class SysUserToken implements Serializable {
    private Long userId;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}