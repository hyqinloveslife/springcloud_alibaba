package com.hyqin.controller;

import com.hyqin.config.Api_System;
import com.hyqin.dao.SysMenuDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.SysMenuQueryDTO;
import com.hyqin.entity.SysMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

}
