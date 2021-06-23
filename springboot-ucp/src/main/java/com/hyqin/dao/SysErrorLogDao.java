package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.entity.SysErrorLog;

public interface SysErrorLogDao extends BaseMapper<SysErrorLog> {
    int deleteByPrimaryKey(Long id);

    int insert(SysErrorLog record);

    int insertSelective(SysErrorLog record);

    SysErrorLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysErrorLog record);

    int updateByPrimaryKey(SysErrorLog record);
}