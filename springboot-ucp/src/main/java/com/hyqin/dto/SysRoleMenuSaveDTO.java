package com.hyqin.dto;

import lombok.Data;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/28  9:55
 */
@Data
public class SysRoleMenuSaveDTO {
    private Long roleId;

    private Long[] menuId;
}
