package com.hyqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyqin.config.Api_System;
import com.hyqin.dao.SysUserDao;
import com.hyqin.dao.SysUserTokenDao;
import com.hyqin.domain.config.R;
import com.hyqin.domain.config.ResponseEnum;
import com.hyqin.dto.SysLoginDTO;
import com.hyqin.entity.SysUser;
import com.hyqin.entity.SysUserToken;
import com.hyqin.util.EncryptedUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

/**
 * @description 处理登录，注册，登出，获取token等相关操作
 * @author: huangyeqin
 * @create : 2021/6/24  9:42
 */
@Slf4j
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
        SysUser sysUser = sysUserDao.selectOne(queryWrapper);
        if (Objects.isNull(sysUser)) {
            //return R.error("账号不存在", null);
            throw ResponseEnum.RESPONSE_CODE_ACCOUNT_NOT_FOUND.getBusinessException();
        }
        String salt = sysUser.getSalt();
        String password = sysUser.getPassword();
        String inputPassword = EncryptedUtil.MD5WithoutSalt(loginDTO.getPassword() + salt);
        if (!inputPassword.equalsIgnoreCase(password)) {
            throw ResponseEnum.RESPONSE_CODE_PASSWORD_ERROR.getBusinessException();
            //return R.error("密码错误", null);
        }
        //queryWrapper.lambda().eq(SysUser::getPassword, loginDTO.getPassword());

        // 获取token
        SysUserToken sysUserToken = userTokenDao.selectByPrimaryKey(sysUser.getId());
        if (Objects.isNull(sysUserToken)) {
            return R.error("当前用户未注册", null);
        }
        return R.success("登录成功", sysUserToken);
    }

    @ApiOperation("注册")
    @PostMapping("/sys/sign")
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public R signInHandler(@RequestBody SysUser saveUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, saveUser.getUsername());
        SysUser sysUser = sysUserDao.selectOne(queryWrapper);
        if (sysUser != null) {
            return R.error("用户已经注册，请直接登录", null);
        }

        // 1、保存到人员信息表
        String salt = EncryptedUtil.getSalt();
        saveUser.setSalt(salt);
        String password = EncryptedUtil.generateDefaultPassword();
        saveUser.setPassword(EncryptedUtil.MD5WithoutSalt(password + salt));
        int result = sysUserDao.insertSelective(saveUser);
        if (result == 0) {
            return R.error("注册失败", null);
        }

        // 2、保存token到redis
        SysUserToken record = new SysUserToken();
        record.setUserId(saveUser.getId());
        record.setToken(UUID.randomUUID().toString());
        int i = userTokenDao.insertSelective(record);
        if (i == 0) {
            return R.error("注册失败", null);
        }

        return R.success("注册成功", null);
    }

    @ApiOperation("登出")
    @GetMapping("/logout")
    public R signOutHandler(@RequestParam("token") String token) {
        // 1、保存到人员信息表
        log.info(">>>>>>>登出{}>>>>>>", token);
        // 2、保存token到redis

        return R.success("注销成功", null);
    }
}
