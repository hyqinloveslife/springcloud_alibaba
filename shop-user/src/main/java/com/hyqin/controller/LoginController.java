package com.hyqin.controller;

import com.hyqin.domain.config.R;
import com.hyqin.service.ILoginService;
import com.hyqin.service.IUserService;
import com.hyqin.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 登录
 * @author: huangyeqin
 * @create : 2021/4/22  15:52
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/web/sys")
public class LoginController {
  /*
    登录页面，需要做如下几个功能，登录，注册，登出
    以后还可以集成手机验证，第三方登录等相关功能，特别是集成微信登录
  */

  @Resource
  private ILoginService loginService;

  // 登录，先从前端获取用户名和密码
  @ApiOperation("用户登录")
  @PostMapping(value = "/do-login")
  public R doLogin(@RequestBody UserVo userVo){
    return loginService.doLogin(userVo);
  }
}
