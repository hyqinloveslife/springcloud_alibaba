package com.hyqin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyqin.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
