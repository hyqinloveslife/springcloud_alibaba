package com.hyqin.auth;

import cn.dev33.satoken.stp.StpInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @description 获取当前账号权限码集合
 * @author: huangyeqin
 * @create : 2021/3/10  21:15
 */
@Component
public class StpInterfaceImpl implements StpInterface {

  /**
   * 返回一个账号所拥有的权限码集合
   * @Desc :
   * @Author : huangyeqin
   * @Date : 2021/3/10 21:16
   * @Param : loginId 登录id
              loginKey
   * @Result : java.util.List<java.lang.String>
  */
  public List<String> getPermissionList(Object loginId, String loginKey) {
    // 返回权限集合
    List<String> list = new ArrayList<String>();


    return list;
  }

  /**
   *
   * @Desc : 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
   * @Author : huangyeqin
   * @Date : 2021/3/10 21:18
   * @Param : loginId
              loginKey
   * @Result : java.util.List<java.lang.String>
  */
  public List<String> getRoleList(Object loginId, String loginKey) {
    List<String> list = new ArrayList<String>();


    return list;
  }
}
