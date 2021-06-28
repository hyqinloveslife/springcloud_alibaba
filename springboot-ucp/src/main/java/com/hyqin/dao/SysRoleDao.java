package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.dto.SysRoleQueryDTO;
import com.hyqin.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysRoleDao extends BaseMapper<SysRole> {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> listRoles(SysRoleQueryDTO queryDto);
}