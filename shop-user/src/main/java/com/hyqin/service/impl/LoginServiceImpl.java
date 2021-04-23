package com.hyqin.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.hyqin.domain.config.R;
import com.hyqin.entity.UserDO;
import com.hyqin.service.ILoginService;
import com.hyqin.service.IUserService;
import com.hyqin.vo.UserVo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/4/22  16:04
 */
@Service
public class LoginServiceImpl implements ILoginService {

  @Resource
  private IUserService userService;

  @Override
  public R doLogin(UserVo userVo) {

    // 验证账号密码
    List<UserDO> users = userService.query(userVo);
    if (CollectionUtils.isEmpty(users)){
      return R.error("账号或密码错误，请重试",null);
    }
    // 保存账号密码到so-token
    StpUtil.setLoginId(userVo.getAccount());

    // 返回一个token给前端
    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

    return R.success(tokenInfo);
  }
}
