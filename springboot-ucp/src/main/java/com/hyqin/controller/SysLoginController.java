package com.hyqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyqin.config.Api_System;
import com.hyqin.dao.SysUserDao;
import com.hyqin.dao.SysUserTokenDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.SysLoginDTO;
import com.hyqin.entity.SysUser;
import com.hyqin.entity.SysUserToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description 处理登录，注册，登出，获取token等相关操作
 * @author: huangyeqin
 * @create : 2021/6/24  9:42
 */
@Api(tags = "系统管理-登录")
@Api_System
@RequestMapping("/sys")
@RestController
public class SysLoginController extends BaseController {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserTokenDao userTokenDao;

    @ApiOperation("登录")
    @PostMapping("/login")
    public R loginHandler(@RequestBody SysLoginDTO loginDTO) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, loginDTO.getUsername());
        queryWrapper.lambda().eq(SysUser::getPassword, loginDTO.getPassword());
        SysUser sysUser = sysUserDao.selectOne(queryWrapper);
        if (Objects.isNull(sysUser)) {
            return R.error("登录失败");
        }

        // 获取token
        SysUserToken sysUserToken = userTokenDao.selectByPrimaryKey(sysUser.getId());
        if (Objects.isNull(sysUserToken)) {
            return R.error("当前用户未注册");
        }
        return R.success("登录成功", sysUserToken);
    }

    @ApiOperation("注册")
    @PostMapping("/sys/sign")
    public R signInHandler(@RequestBody SysUser saveUser){
        // 1、保存到人员信息表

        // 2、保存token到redis

        return R.success("注册成功");
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public R signOutHandler(@RequestBody SysUser saveUser){
        // 1、保存到人员信息表

        // 2、保存token到redis

        return R.success("注销成功");
    }
}
