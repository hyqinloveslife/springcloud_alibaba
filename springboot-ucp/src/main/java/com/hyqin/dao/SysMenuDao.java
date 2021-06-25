package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.dto.SysMenuQueryDTO;
import com.hyqin.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysMenuDao extends BaseMapper<SysMenu> {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> listMenu(SysMenuQueryDTO queryDto);
}