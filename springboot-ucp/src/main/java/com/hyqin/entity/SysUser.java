package com.hyqin.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.apache.ibatis.annotations.Options;

/**
 * sys_user
 * @author 
 */
@Data
public class SysUser implements Serializable {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

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
     * 头像
     */
    private String avatar;

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
    private Date createdTime;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 更新人
     */
    private Long updatedBy;

    /**
     * 是否已删除: 0有效，1失效
     */
    private Byte isDeleted;

    private static final long serialVersionUID = 1L;
}