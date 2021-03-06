# springcloud alibaba项目框架
搭建一个微服务框架，使用了spring cloud框架，集成了redis，elastic search，rabbitmq，等相关中间件

## 1、文件服务器 file-system
基于fastdfs搭建的文件服务器，在业务端进行了封装

## 2、业务模块
业务模块包含
+ 商品服务
+ 订单服务
+ 会员服务
+ 评论服务

## 3、spring-cloud-gateway网关模块
网关模块，可以实现拦截，限流等功能

## 4、xxl-job 分布式任务调度框架
分布式的任务调度

## 5、rbac权限框架（开发中...）
### 5.1 rbac基础框架搭建
基础rbac0版本，实现简单的权限框架，后续版本会继续升级到rbac3版本，实现权限分组等功能
+ 登录用户管理，包括给登录用户修改密码，修改基本信息，配置角色
+ 角色，给角色配置菜单资源和权限
+ 菜单资源

### 5.2 全局操作日志记录
<p>在后续的版本中，为了保证记录日志不影响业务的调度，操作日志的记录将会用消息队列的方式进行异步处理</p>
<p>新版本中，过滤器不再拦截日志相关的接口，下一步将会完成登录接口</p>

### 5.3 全局异常日志记录
在消息队列的基础上，由于堆栈信息过多，将会用NoSql的方式记录全局异常日志和操作日志
<p>已经集成mongodb，并可以进行分页查询</p>

### 5.4 统一登录验证
<p>在接下来的统一登录验证中，将会采用redis去记录token，并设置token的有效期</p>

## 6、统一认证中心框架（待开发） 
统一认证中心框架，所有的业务系统都要集成这个模块，对当前登录的用户进行身份验证，在全局中获取当前登录人信息


## 7、集成so-token
shop-user模块原本是用来集成rbac权限框架的，但是目前用来集成so-token框架，看一下这个开源框架最后能实现什么功能。

