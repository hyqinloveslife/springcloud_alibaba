package com.hyqin.dao;

import com.hyqin.entity.SysErrorLog;

public interface SysErrorLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysErrorLog record);

    int insertSelective(SysErrorLog record);

    SysErrorLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysErrorLog record);

    int updateByPrimaryKey(SysErrorLog record);
}