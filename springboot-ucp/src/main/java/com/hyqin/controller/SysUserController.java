package com.hyqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.hyqin.config.Api_System;
import com.hyqin.dao.SysUserDao;
import com.hyqin.dao.SysUserRoleDao;
import com.hyqin.dao.SysUserTokenDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.SysUserQueryDTO;
import com.hyqin.dto.SysUserRoleSaveDTO;
import com.hyqin.entity.SysUser;
import com.hyqin.entity.SysUserRole;
import com.hyqin.entity.SysUserToken;
import com.hyqin.util.EncryptedUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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

    @Resource
    private SysUserRoleDao userRoleDao;

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
        queryWrapper.lambda().eq(StringUtils.isNotBlank(queryDTO.getUsername()), SysUser::getUsername, queryDTO.getUsername());
        queryWrapper.lambda().like(StringUtils.isNotBlank(queryDTO.getRealName()), SysUser::getRealName, queryDTO.getRealName());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(queryDTO.getIdCard()), SysUser::getIdCard, queryDTO.getIdCard());
        queryWrapper.lambda().eq(StringUtils.isNotBlank(queryDTO.getMobile()), SysUser::getMobile, queryDTO.getMobile());
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
        updateUser.setUpdatedTime(new Date());
        updateUser.setUpdatedBy(1L);
        int result = sysUserDao.updateByPrimaryKeySelective(updateUser);
        if (result == 0) {
            return R.error("修改失败", null);
        }
        return R.success("修改成功", null);
    }

    @ApiOperation("删除用户信息-物理删除")
    @PostMapping("/delete-user")
    public R deleteUser(@RequestBody SysUser deleteUser) {
        // 删除用户对应的角色
        QueryWrapper<SysUserRole> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.lambda().eq(SysUserRole::getUserId, deleteUser.getId());
        userRoleDao.delete(deleteWrapper);

        // 删除用户
        int result = sysUserDao.deleteByPrimaryKey(deleteUser.getId());
        return result == 1 ? R.success("删除成功", null) : R.error("删除失败", null);
    }

    @ApiOperation("获取用户对应的角色信息")
    @PostMapping("/user-roles")
    public R getUserRoles(@RequestBody SysUserRoleSaveDTO queryDTO) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserRole::getUserId, queryDTO.getUserId());
        List<SysUserRole> sysUserRoles = userRoleDao.selectList(queryWrapper);
        List<Long> roleIds = sysUserRoles.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
        return R.success(roleIds);
    }

    @ApiOperation("保存用户的角色")
    @PostMapping("/save-user-role")
    public R saveUserRole(@RequestBody SysUserRoleSaveDTO saveDTO) {
        QueryWrapper<SysUserRole> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.lambda().eq(SysUserRole::getUserId, saveDTO.getUserId());
        userRoleDao.delete(deleteWrapper);

        List<SysUserRole> list = new ArrayList<>();
        for (Long roleId : saveDTO.getRoleId()) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(saveDTO.getUserId());
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        userRoleDao.insertBatch(list);

        return R.success("保存成功", null);
    }

    @ApiOperation("修改密码")
    @PostMapping("/modify-password")
    public R modifyPassword(@RequestBody SysUser updateUser){
        String salt = EncryptedUtil.getSalt();
        updateUser.setPassword(EncryptedUtil.MD5WithoutSalt(updateUser.getPassword()+salt));
        updateUser.setSalt(salt);
        int result = sysUserDao.updateByPrimaryKeySelective(updateUser);
        if (result == 0) {
            return R.error("修改失败", null);
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
