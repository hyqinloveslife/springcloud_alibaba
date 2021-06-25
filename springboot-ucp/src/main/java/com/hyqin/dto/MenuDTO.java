package com.hyqin.dto;

import com.hyqin.entity.SysMenu;
import lombok.Data;

import java.util.List;

/**
 * @description 返回菜单树给前端
 * @author: huangyeqin
 * @create : 2021/6/25  8:43
 */
@Data
public class MenuDTO extends SysMenu {
    private String label;

    private List<MenuDTO> children;

    public void setLabel(String label) {
        this.label = this.getName();
    }
}
