package com.hyqin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.hyqin.domain.config.R;
import com.hyqin.entity.UserDO;
import com.hyqin.service.IUserService;
import com.hyqin.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 在这个类中，编写关于用户的基本信息
 * @description
 * @author: hyqin
 * @create : 2020/9/5
 */
@Api(tags = "用户的基本信息接口")
@RestController
@RequestMapping("/web/base/userinfo")
public class UserController {

  @Resource
  private IUserService userService;

//  @SaCheckLogin  // 登录验证
  @ApiOperation(value = "获取当前用户的基本信息")
  @GetMapping("/get-user")
  public R<UserDO> getUserInfo(){
    return userService.getUserInfo();
  }

  @ApiOperation(value = "更新用户的基本信息")
  @GetMapping("/update-user")
  public R updateUserInfo(UserVo userVo){

    return userService.updateUserInfo(userVo);
  }

}
