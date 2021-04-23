package com.hyqin.service;

import com.hyqin.domain.config.R;
import com.hyqin.vo.UserVo;

/**
 * @description 登录处理
 * @author: huangyeqin
 * @create : 2021/4/22  16:03
 */
public interface ILoginService {
  /**
   * 登录
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/4/22 16:02
   * @Param : userVo
   * @Result : com.hyqin.domain.config.R
   */
  R doLogin(UserVo userVo);
}
