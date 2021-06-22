package com.hyqin.config;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description 自定义侦听器的实现
 * @author: huangyeqin
 * @create : 2021/4/23  14:03
 */
@Slf4j
@Component
public class MySaTokenListener implements SaTokenListener {

  /** 每次登录时触发 */
  @Override
  public void doLogin(String loginKey, Object loginId, SaLoginModel saLoginModel) {
    log.info(">>>>>>>>>>用户登录>>>>>>>>>>>");
    log.info("登录名：{}，登录id：{}，loginModel：{}",loginKey,loginId,saLoginModel);
  }

  /** 每次注销时触发 */
  @Override
  public void doLogout(String loginKey, Object loginId, String token) {
    log.info(">>>>>>>>>>用户登出>>>>>>>>>>>");
    log.info("登录名：{}，登录id：{}，token：{}",loginKey,loginId,token);
  }

  /** 每次被踢下线时触发 */
  @Override
  public void doLogoutByLoginId(String s, Object o, String s1, String s2) {

  }

  /** 每次被顶下线时触发 */
  @Override
  public void doReplaced(String s, Object o, String s1, String s2) {

  }

  /** 每次被封禁时触发 */
  @Override
  public void doDisable(String s, Object o, long l) {

  }

  /** 每次被解封时触发 */
  @Override
  public void doUntieDisable(String s, Object o) {

  }

  /** 每次创建Session时触发 */
  @Override
  public void doCreateSession(String s) {

  }

  /** 每次注销Session时触发 */
  @Override
  public void doLogoutSession(String s) {

  }
}
