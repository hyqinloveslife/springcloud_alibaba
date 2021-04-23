## 关于so-token框架的开发

### 集成方式：在spring boot环境下集成
设置依赖
```xml
<!-- Sa-Token 权限认证, 在线文档：http://sa-token.dev33.cn/ -->
<dependency>
    <groupId>cn.dev33</groupId>
    <artifactId>sa-token-spring-boot-starter</artifactId>
    <version>1.17.0</version>
</dependency>

```

设置配置文件
```yaml
server:
    # 端口
    port: 8081

spring: 
    # sa-token配置
    sa-token: 
        # token名称 (同时也是cookie名称)
        token-name: satoken
        # token有效期，单位s 默认30天, -1代表永不过期 
        timeout: 2592000
        # token临时有效期 (指定时间内无操作就视为token过期) 单位: 秒
        activity-timeout: -1
        # 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录) 
        allow-concurrent-login: false
        # 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token) 
        is-share: false
        # token风格
        token-style: uuid
        # 是否输出操作日志 
        is-log: false
```

### 开发方式
可能需要写一个统一的拦截器，在拦截器里面去验证token
> 一个好的框架，连我都能想得到需要将业务逻辑和框架逻辑分开，作者怎么可能想不到呢？所以这里提供了aop和拦截器两种鉴权方式

- `@SaCheckLogin:` 标注在方法或类上，当前会话必须处于登录状态才可通过校验
- `@SaCheckRole("admin"):` 标注在方法或类上，当前会话必须具有指定角色标识才能通过校验
- `@SaCheckPermission("user:add"):` 标注在方法或类上，当前会话必须具有指定权限才能通过校验

#### 1、使用aop模式
```xml
<!-- sa-token整合SpringAOP实现注解鉴权 -->
<dependency>
    <groupId>cn.dev33</groupId>
    <artifactId>sa-token-spring-aop</artifactId>
    <version>1.17.0</version>
</dependency>
```

在Java代码中的使用
```java
// 注解式鉴权：当前会话必须登录才能通过 

public class Client{
    // 注解式鉴权：当前会话必须登录才能通过
    @SaCheckLogin                        
    @RequestMapping("info")
    public String info() {
        return "查询用户信息";
    }

    // 注解式鉴权：当前会话必须具有指定角色标识才能通过 
    @SaCheckRole("super-admin")        
    @RequestMapping("add")
    public String add() {
        return "用户增加";
    }
    // 注解式鉴权：当前会话必须具有指定权限才能通过 
    @SaCheckPermission("user-add")        
    @RequestMapping("add")
    public String add() {
        return "用户增加";
    }
}
```
但可以看出，这种方式是不够好的，如果我对外暴露的接口过多的时候，容易写错，也容易忘记;所以更推荐用拦截器的方式，进行统一拦截

#### 2、使用拦截器模式
使用拦截器是不需要另外引入新的pom依赖的，所以更加方便，但要注意，aop和拦截器不能同时使用，不然就会导致验证两次的bug
```java
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册sa-token的注解拦截器，打开注解式鉴权功能 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");    
    }
}
```

#### 3、路由拦截式鉴权
其实一般的项目中，都会有这么个需求
> 项目中所有的接口都需要登录验证，只有登录接口对外开放

那么这种需求，就需要用路由拦截式鉴权来实现
```java
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册sa-token的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则 
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {

            // 登录验证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录 
            SaRouterUtil.match("/**", "/user/doLogin", () -> StpUtil.checkLogin());

            // 登录验证 -- 排除多个路径
            SaRouterUtil.match(Arrays.asList("/**"), Arrays.asList("/user/doLogin", "/user/reg"), () -> StpUtil.checkLogin());

            // 角色认证 -- 拦截以 admin 开头的路由，必须具备[admin]角色或者[super-admin]角色才可以通过认证 
            SaRouterUtil.match("/admin/**", () -> StpUtil.checkRoleOr("admin", "super-admin"));

            // 权限认证 -- 不同模块, 校验不同权限 
            SaRouterUtil.match("/user/**", () -> StpUtil.checkPermission("user"));
            SaRouterUtil.match("/admin/**", () -> StpUtil.checkPermission("admin"));
            SaRouterUtil.match("/goods/**", () -> StpUtil.checkPermission("goods"));
            SaRouterUtil.match("/orders/**", () -> StpUtil.checkPermission("orders"));
            SaRouterUtil.match("/notice/**", () -> StpUtil.checkPermission("notice"));
            SaRouterUtil.match("/comment/**", () -> StpUtil.checkPermission("comment"));

            // 匹配 restful 风格路由 
            SaRouterUtil.match("/article/get/{id}", () -> StpUtil.checkPermission("article"));

            // 检查请求方式 
            SaRouterUtil.match("/notice/**", () -> {
                if(request.getMethod().equals(HttpMethod.GET.toString())) {
                    StpUtil.checkPermission("notice");
                }
            });

            // 在多账号模式下，可以使用任意StpUtil进行校验
            SaRouterUtil.match("/user/**", () -> StpUserUtil.checkLogin());

        })).addPathPatterns("/**");
    }
}

```

