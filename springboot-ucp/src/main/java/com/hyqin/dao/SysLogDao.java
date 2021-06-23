package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyqin.dto.SysLogQueryDTO;
import com.hyqin.entity.SysLog;

import java.util.List;

public interface SysLogDao extends BaseMapper<SysLog> {
    int deleteByPrimaryKey(Long id);

    @Override
    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    List<SysLog> listLogs(SysLogQueryDTO queryDTO);

    //IPage<SysLog> selectPage(Page page , SysLogQueryDTO queryDTO);
}