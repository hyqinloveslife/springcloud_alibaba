package com.hyqin.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.hyqin.config.Api_Business;
import com.hyqin.config.Api_System;
import com.hyqin.dao.SysUserDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.SysUserQueryDTO;
import com.hyqin.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @description 用户信息
 * @author: huangyeqin
 * @create : 2021/6/21  16:04
 */
@Api(tags = "系统管理-用户信息管理")
@Api_System
@RequestMapping("/sys-user")
@RestController
public class SysUserController {

    @Resource
    private SysUserDao sysUserDao;

    @ApiOperation("查询用户信息")
    @PostMapping("/list-user")
    public R list(@RequestBody SysUserQueryDTO queryDTO){
        List<SysUser> userList = sysUserDao.listUsers(queryDTO);
        if (CollectionUtils.isEmpty(userList)){
            return R.success(Collections.emptyList());
        }
        return R.success(userList);
    }

    @ApiOperation("保存用户信息")
    @PostMapping("/save-user")
    public R save(@RequestBody SysUser saveUser){
        int result = sysUserDao.insertSelective(saveUser);
        if (result == 0){
            return R.error("保存失败");
        }
        return R.success("保存成功");
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update-user")
    public R update(@RequestBody SysUser updateUser){
        int result = sysUserDao.updateByPrimaryKeySelective(updateUser);
        if (result == 0 ){
            return R.error("修改失败");
        }
        return R.success("修改成功");
    }
}
