package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleDao extends BaseMapper<SysUserRole> {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    int insertBatch(List<SysUserRole> list);
}