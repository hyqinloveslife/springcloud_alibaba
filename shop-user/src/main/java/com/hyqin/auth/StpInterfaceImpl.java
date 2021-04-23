package com.hyqin.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyqin.dao.MenuMapper;
import com.hyqin.dao.PermissionMapper;
import com.hyqin.entity.MenuInfoDO;
import com.hyqin.entity.PermissionDO;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @description 自定义权限验证接口扩展
 * @author: huangyeqin
 * @create : 2021/3/10  21:15
 */
@Component    // 保证此类被SpringBoot扫描，完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

  @Resource
  private PermissionMapper permissionMapper;

  @Resource
  private MenuMapper menuMapper;

  @Override
  public List<String> getPermissionList(Object loginId, String loginKey) {
    List<MenuInfoDO> list = menuMapper
        .selectList(new QueryWrapper<MenuInfoDO>().lambda().eq(MenuInfoDO::getMenuType,
            "2"));
    List<String> collect = list.stream().map(e -> e.getMenuPath()).collect(Collectors.toList());
    return collect;
  }

  @Override
  public List<String> getRoleList(Object loginId, String loginKey) {
    List<PermissionDO> list = permissionMapper.selectList(null);
    List<String> collect = list.stream().map(e -> e.getName()).collect(Collectors.toList());
    return collect;
  }
}
