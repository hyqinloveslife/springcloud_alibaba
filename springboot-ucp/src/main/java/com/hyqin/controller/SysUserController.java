package com.hyqin.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.hyqin.config.Api_Business;
import com.hyqin.config.Api_System;
import com.hyqin.dao.SysUserDao;
import com.hyqin.dao.SysUserTokenDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.SysUserQueryDTO;
import com.hyqin.entity.SysUser;
import com.hyqin.entity.SysUserToken;
import com.hyqin.util.EncryptedUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description 用户信息
 * @author: huangyeqin
 * @create : 2021/6/21  16:04
 */
@Api(tags = "系统管理-用户信息管理")
@Api_System
@RequestMapping("/sys/user")
@RestController
public class SysUserController extends BaseController {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserTokenDao userTokenDao;

    @ApiOperation("查询用户信息")
    @PostMapping("/list-user")
    public R list(@RequestBody SysUserQueryDTO queryDTO) {
        List<SysUser> userList = sysUserDao.listUsers(queryDTO);
        if (CollectionUtils.isEmpty(userList)) {
            return R.success(Collections.emptyList());
        }
        return R.success(userList);
    }

    @ApiOperation("查询用户信息-分页查询")
    @PostMapping("/list-user-page")
    public R listUserPage(@RequestBody SysUserQueryDTO queryDTO) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().orderByDesc(SysUser::getCreatedTime);
        IPage<SysUser> page = sysUserDao.selectPage(new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize()),
                queryWrapper);
        return R.success(page);
    }


    @ApiOperation("保存用户信息")
    @PostMapping("/save-user")
    public R save(@RequestBody SysUser saveUser) {
        String defaultPassword = EncryptedUtil.generateDefaultPassword();
        if (Strings.isNullOrEmpty(saveUser.getPassword()) && Strings.isNullOrEmpty(saveUser.getSalt())) {
            saveUser.setPassword(EncryptedUtil.MD5WithSalt(defaultPassword + EncryptedUtil.getSalt()));
            saveUser.setSalt(EncryptedUtil.getSalt());
        }
        int result = sysUserDao.insertSelective(saveUser);
        if (result == 0) {
            return R.error("保存失败");
        }

        // 用户信息同步到token表
        SysUserToken userToken = new SysUserToken();
        userToken.setUserId(saveUser.getId());
        userToken.setToken(UUID.randomUUID().toString());
        userToken.setUpdateTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 3);
        userToken.setExpireTime(calendar.getTime());
        int i = userTokenDao.insertSelective(userToken);
        if (i == 0) {
            return R.error("新增失败", null);
        }

        saveUser.setPassword(defaultPassword);
        return R.success("保存成功", saveUser);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update-user")
    public R update(@RequestBody SysUser updateUser) {
        updateUser.setPassword(EncryptedUtil.MD5WithSalt(updateUser.getPassword() + EncryptedUtil.getSalt()));
        updateUser.setSalt(EncryptedUtil.getSalt());
        updateUser.setUpdatedTime(new Date());
        updateUser.setUpdatedBy(1L);
        int result = sysUserDao.updateByPrimaryKeySelective(updateUser);
        if (result == 0) {
            return R.error("修改失败",null);
        }
        return R.success("修改成功",null);
    }

    @ApiOperation("根据token查询用户信息")
    @GetMapping("/info")
    public R userInfo(@RequestParam("token") String token) {
        QueryWrapper<SysUserToken> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserToken::getToken, token);
        SysUserToken sysUserToken = userTokenDao.selectOne(queryWrapper);
        if (Objects.isNull(sysUserToken)) {
            return R.error("当前用户未登录");
        }

        // 查用户信息
        SysUser sysUser = sysUserDao.selectByPrimaryKey(sysUserToken.getUserId());
        if (Objects.isNull(sysUser)) {
            return R.error("未查询到用户信息");
        }
        return R.success("success", sysUser);
    }
}
