package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.entity.MenuInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 菜单资源mapper
 * @author: huangyeqin
 * @create : 2021/4/22  15:25
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuInfoDO> {

}
