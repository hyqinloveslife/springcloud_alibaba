package com.hyqin.controller;

import com.hyqin.config.Api_System;
import com.hyqin.dao.SysRoleDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.SysRoleQueryDTO;
import com.hyqin.entity.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/21  16:04
 */
@Api(tags = "系统管理-权限管理")
@RequestMapping("/sys-role")
@Api_System
@RestController
public class SysRoleController {

    @Resource
    private SysRoleDao sysRoleDao;

    @ApiOperation("列表查询-权限信息")
    @PostMapping("/list-role")
    public R list(@RequestBody SysRoleQueryDTO queryDto){
        List<SysRole> sysRoles = sysRoleDao.listRoles(queryDto);
        return R.success(sysRoles);
    }

    @ApiOperation("新增权限信息")
    @PostMapping("/save-role")
    public R save(@RequestBody SysRole saveRole){
        int result = sysRoleDao.insertSelective(saveRole);
        if (result == 0){
            return R.error("新增失败");
        }
        return R.success("保存成功");
    }
}
