package com.hyqin.service;

import com.hyqin.domain.config.R;
import com.hyqin.entity.UserDO;
import com.hyqin.vo.UserVo;
import java.util.List;

/**
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
public interface IUserService {

  List<UserDO> query(UserVo userVo);
}
