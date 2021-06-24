package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.entity.SysUserToken;

public interface SysUserTokenDao extends BaseMapper<SysUserToken> {
    int deleteByPrimaryKey(Long userId);

    int insert(SysUserToken record);

    int insertSelective(SysUserToken record);

    SysUserToken selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUserToken record);

    int updateByPrimaryKey(SysUserToken record);
}