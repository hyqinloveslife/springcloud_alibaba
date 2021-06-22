package com.hyqin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hyqin.dao.UserMapper;
import com.hyqin.domain.config.R;
import com.hyqin.entity.UserDO;
import com.hyqin.service.IUserService;
import com.hyqin.vo.UserVo;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
@Service
public class UserServiceImpl implements IUserService {

  @Resource
  private UserMapper userMapper;

  @Override
  public List<UserDO> query(UserVo userVo) {
    QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
    queryWrapper.lambda()
        .eq(StringUtils.isNotBlank(userVo.getAccount()), UserDO::getAccount, userVo.getAccount());
    // 由于数据库中是存的密文，所以在前一步就要进行加密
    queryWrapper.lambda().eq(StringUtils.isNotBlank(userVo.getPassword()), UserDO::getPassword,
        userVo.getPassword());
    List<UserDO> users = userMapper.selectList(queryWrapper);
    return users;
  }

  @Override
  public R<UserDO> getUserInfo() {

    // 写一个临时的返回内容
    return R.success(new UserDO());
  }

  @Override
  public R updateUserInfo(UserVo userVo) {

    return R.success("修改成功",null);
  }
}
