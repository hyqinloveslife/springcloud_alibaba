package com.hyqin.dto;

import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/22  16:13
 */
@Data
public class SysUserQueryDTO extends BaseDTO{
    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 数据权限类型 0: 参与项目  1：负责部门  2：自定义
     */
    private Byte scopeType;

    /**
     * 状态  0：禁用   1：正常
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTimeStart;

    /**
     * 创建-结束时间
     */
    private Date createTimeEnd;

    /**
     * 是否已删除: 0有效，1失效
     */
    private Byte isDeleted;
}
