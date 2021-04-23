# springcloud alibaba项目框架
搭建一个微服务框架，使用了spring cloud框架，集成了redis，elastic search，rabbitmq，等相关中间件

## 1、文件服务器 file-system
基于fastdfs搭建的文件服务器，在业务端进行了封装

## 2、业务模块
业务模块包含

## 3、spring-cloud-gateway网关模块
网关模块，可以实现拦截，限流等功能

## 4、xxl-job 分布式任务调度框架
分布式的任务调度

## 5、rbac权限框架（未开发）

## 6、统一认证中心框架（待开发） 
springboot-ucp是统一认证中心框架，所有的业务系统都要集成这个模块


## 7、集成so-token
shop-user模块原本是用来集成rbac权限框架的，但是目前用来集成so-token框架，看一下这个开源框架最后能实现什么功能。

