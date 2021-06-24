package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.dto.SysUserQueryDTO;
import com.hyqin.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface SysUserDao extends BaseMapper<SysUser> {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> listUsers(SysUserQueryDTO queryDto);
}