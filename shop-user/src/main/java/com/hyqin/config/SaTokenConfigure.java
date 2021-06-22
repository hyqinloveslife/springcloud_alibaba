package com.hyqin.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description 自定义拦截器鉴权
 * @author: huangyeqin
 * @create : 2021/4/23  9:57
 */
@Configuration
@ConditionalOnProperty(
  name = {"security.type"},
  havingValue ="hsa-sso"
)
public class SaTokenConfigure implements WebMvcConfigurer {

  @Value("${security.oauth2.client.permits:'/hsaf_pass'}")
  private String permits;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    // 注册sa-token的拦截器
    registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
      // 登录验证 -- 拦截所有，除了登录接口
//      SaRouterUtil.match("/**", "/web/sys/do-login", () -> StpUtil.checkLogin());
      System.out.println(">>>>>>>>>>>>>>>打印一下permist {"+permits+"}>>>>>>>>>>>>>>>>");
      String[] permistList = this.permits.split(",");
      // 登录验证 -- 排除多个路径
      SaRouterUtil
          .match(Arrays.asList("/**"), Arrays.asList(permistList),
              () -> StpUtil.checkLogin());
//      SaRouterUtil
//          .match(Arrays.asList("/**"), Arrays.asList("/user/web/sys/do-login", "/user/web/sys/do-sign"),
//              () -> StpUtil.checkLogin());

      // 其实下面的这些认证，可以另外封装起来，作为认证中心的接口，而统一认证中心，只用来验证是否登录
      // 角色认证 -- 拦截以admin开头的路由，必须具备[admin]角色或者[super-admin]角色才可以通过认证
//      SaRouterUtil.match("/admin/**",()->StpUtil.checkRoleOr("admin","super-admin"));

      // 权限认证 -- 不同模块，校验不同权限
//      SaRouterUtil.match("/user/**",()->StpUtil.checkPermission("user"));

      // 匹配restful api风格的路由
//      SaRouterUtil.match("/article/get/{id}",()->StpUtil.checkPermission("article"));

//      // 检查请求方式
//      SaRouterUtil.match("/notice/**",()->{
//        if (HttpMethod.GET.toString().equalsIgnoreCase(request.getHeader(""))){
//          StpUtil.checkPermission("notice");
//        }
//      });
    })).addPathPatterns("/**");
  }
}
