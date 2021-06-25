package com.hyqin.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.hyqin.config.Api_System;
import com.hyqin.dao.SysMenuDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.MenuDTO;
import com.hyqin.dto.SysMenuQueryDTO;
import com.hyqin.entity.SysMenu;
import com.hyqin.utils.BeanHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description 系统管理-菜单资源管理
 * @author: huangyeqin
 * @create : 2021/6/22  19:14
 */
@Api_System
@Api(tags = "系统管理-菜单管理")
@RequestMapping("/sys/menu")
@RestController
public class SysMenuController {

    @Resource
    private SysMenuDao sysMenuDao;

    @ApiOperation("列表查询-菜单资源信息")
    @PostMapping("/list-menu")
    public R list(@RequestBody SysMenuQueryDTO queryDTO) {
        List<SysMenu> sysMenus = sysMenuDao.listMenu(queryDTO);
        return R.success(sysMenus);
    }

    @ApiOperation("保存菜单资源")
    @PostMapping("/save-menu")
    public R save(@RequestBody SysMenu saveDTO) {
        int result = sysMenuDao.insertSelective(saveDTO);
        return result == 0 ? R.error("保存失败") : R.success("保存成功");
    }

    @ApiOperation("更新菜单资源")
    @PostMapping("/update-menu")
    public R update(@RequestBody SysMenu updateDTO) {
        int result = sysMenuDao.updateByPrimaryKeySelective(updateDTO);
        return result == 0 ? R.error("更新失败") : R.success("更新成功");
    }

    @ApiOperation("删除菜单资源")
    @PostMapping("/delete-menu")
    public R delete(@RequestBody SysMenuQueryDTO deleteDTO) {
        int result = sysMenuDao.deleteByPrimaryKey(deleteDTO.getId());
        return result == 1 ? R.success("删除成功", null) : R.error("删除失败", null);
    }

    @ApiOperation("根据菜单id获取菜单资源")
    @PostMapping("/find-menu")
    public R findTree(@RequestBody SysMenuQueryDTO queryDTO) {
        SysMenu sysMenu = sysMenuDao.selectByPrimaryKey(queryDTO.getId());
        if (Objects.isNull(sysMenu)) {
            return R.error(null);
        }
        return R.success(sysMenu);
    }


    @ApiOperation("获取树形菜单")
    @PostMapping("/menu-tree")
    public R treeMenuList(@RequestBody SysMenuQueryDTO queryDTO) {
        List<SysMenu> sysMenuList = sysMenuDao.listMenu(queryDTO);
        try {
            List<MenuDTO> menuList = BeanHelper.convertToBeanList(sysMenuList, MenuDTO.class);

            List<MenuDTO> menuTree = buildMenuTree(menuList, null);

            return R.success(menuTree);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.success(null);
    }

    /**
     * 构建菜单树
     *
     * @param menuList
     * @param pid
     * @return
     */
    private List<MenuDTO> buildMenuTree(List<MenuDTO> menuList, Long pid) {
        List<MenuDTO> treeList = new ArrayList<>();
        menuList.forEach(menu -> {
            Long parentId = menu.getParentId();
            // 这么写是要解决两个问题；1、Long型的缓存问题，2、空指针问题
            if (StringUtils.equalsIgnoreCase(String.valueOf(parentId), String.valueOf(pid))) {
                Long menuId = menu.getId();
                menu.setChildren(buildMenuTree(menuList, menuId));
                treeList.add(menu);
            }
        });
        return treeList;
    }
}
