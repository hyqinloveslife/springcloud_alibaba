package com.hyqin.dto;

import lombok.Data;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/28  16:44
 */
@Data
public class SysUserRoleSaveDTO {

    private Long userId;

    private Long[] roleId;
}
