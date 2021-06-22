package com.hyqin.exception;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.hyqin.domain.config.R;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description 定义全局异常
 * @author: huangyeqin
 * @create : 2021/4/22  15:39
 */
@ControllerAdvice
public class GlobalException {

  // 在当前类每个方法进入之前触发的操作
  @ModelAttribute
  public void get(HttpServletRequest request){}

  // 全局异常拦截
  @ResponseBody
  @ExceptionHandler
  public R handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 打印堆栈信息
    System.out.println(">>>>>>>>>>>>>>>>>打印全局异常>>>>>>>>>>>>>>>>>>>");
    e.printStackTrace();

    // 不同异常返回不同的状态码
    R json = R.error(null);

    // 这里只针对于几种权限框架的异常
    if (e instanceof NotLoginException){  // 未登录异常
      json.setMessage("调用非白名单，且无法获取用户信息! ");
    }else if (e instanceof NotRoleException){  // 角色异常
      json.setMessage("找不到该角色");
    }else if (e instanceof NotPermissionException){  // 没有权限异常
      json.setMessage("无权限异常");
    }else if (e instanceof DisableLoginException){
      json.setMessage("账号被封禁");
    }else {
      json.setMessage(e.getMessage());
    }
    return json;
  }

}
