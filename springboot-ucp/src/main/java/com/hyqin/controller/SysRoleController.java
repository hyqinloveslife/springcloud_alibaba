package com.hyqin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyqin.config.Api_System;
import com.hyqin.dao.SysRoleDao;
import com.hyqin.dao.SysRoleMenuDao;
import com.hyqin.domain.config.R;
import com.hyqin.dto.SysRoleMenuSaveDTO;
import com.hyqin.dto.SysRoleQueryDTO;
import com.hyqin.entity.SysRole;
import com.hyqin.entity.SysRoleMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/21  16:04
 */
@Api(tags = "系统管理-权限管理")
@RequestMapping("/sys/role")
@Api_System
@RestController
public class SysRoleController {

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysRoleMenuDao roleMenuDao;

    @ApiOperation("列表查询-权限信息")
    @PostMapping("/list-role")
    public R list(@RequestBody SysRoleQueryDTO queryDto) {
        List<SysRole> sysRoles = sysRoleDao.listRoles(queryDto);
        return R.success(sysRoles);
    }

    @ApiOperation("列表分页查询-权限信息")
    @PostMapping("/list-role-page")
    public R listPage(@RequestBody SysRoleQueryDTO queryDTO) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotBlank(queryDTO.getRoleName()), SysRole::getRoleName
                                                        , queryDTO.getRoleName());
        queryWrapper.lambda().orderByDesc(SysRole::getCreatedTime);
        IPage<SysRole> rolePage = sysRoleDao.selectPage(new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize())
                , queryWrapper);
        return R.success(rolePage);
    }

    @ApiOperation("新增权限信息")
    @PostMapping("/save-role")
    public R save(@RequestBody SysRole saveRole) {
        // 这里需要检验重复
        saveRole.setCreatedBy(0L);
        saveRole.setCreatedTime(new Date());
        int result = sysRoleDao.insertSelective(saveRole);
        if (result == 0) {
            return R.error("新增失败",null);
        }
        return R.success("保存成功",null);
    }

    @ApiOperation("修改权限信息")
    @PostMapping("/update-role")
    public R update(@RequestBody SysRole updateRole) {
        updateRole.setUpdatedBy(0L);
        updateRole.setUpdatedTime(new Date());
        int result = sysRoleDao.updateByPrimaryKeySelective(updateRole);
        return result == 1 ? R.success("修改成功", null) : R.error("修改失败", null);
    }

    @ApiOperation("删除角色信息")
    @PostMapping("/delete-role")
    public R deleteRole(@RequestBody SysRole deleteDTO) {
        int result = sysRoleDao.deleteById(deleteDTO.getId());
        return result == 1 ? R.success("删除成功", null) : R.error("删除失败", null);
    }

    /**
     * @Desc : 这里有两种处理方案，一种是删除之前的所有，然后再全部新增。一种是挑选出那些是新增的，哪些是删除的，再分别操作
     * @Author : huangyeqin
     * @Date : 2021/6/28 14:45
     * @Param : saveDTO
     * @Result : com.hyqin.domain.config.R
     **/
    @ApiOperation("给角色配置菜单")
    @PostMapping("/save-menu-role")
    public R saveMenuRole(@RequestBody SysRoleMenuSaveDTO saveDTO) {
        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : saveDTO.getMenuId()) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(saveDTO.getRoleId());
            sysRoleMenu.setMenuId(menuId);
            list.add(sysRoleMenu);
        }
        QueryWrapper<SysRoleMenu> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.lambda().eq(SysRoleMenu::getRoleId, saveDTO.getRoleId());
        roleMenuDao.delete(deleteWrapper);
        int result = roleMenuDao.insertBatch(list);
        System.out.println("result = " + result);
        return R.success("保存成功", null);
    }

    @ApiOperation("根据角色id查找菜单")
    @PostMapping("/find-menu-by-role")
    public R findMenuByRoleId(@RequestBody Map roleMap) {
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRoleMenu::getRoleId, roleMap.get("roleId"));
        List<SysRoleMenu> roleMenus = roleMenuDao.selectList(queryWrapper);
        List<Long> menuIds = roleMenus.stream().map(e -> e.getMenuId()).collect(Collectors.toList());
        return R.success(menuIds);
    }
}
