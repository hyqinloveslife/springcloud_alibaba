package com.hyqin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/22  19:01
 */
@Data
public class SysRoleQueryDTO extends BaseDTO {
    /**
     * 创建时间
     */
    private Date createTimeStart;

    /**
     * 创建-结束时间
     */
    private Date createTimeEnd;

    /**
     * 权限名
     */
    private String roleName;

    /**
     * 权限编码
     */
    private String roleCode;

}
