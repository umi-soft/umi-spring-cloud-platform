# umi-spring-cloud-platform
优米微服务脚手架

+ 本工程的前端为[element-admin branch umi-spring-cloud-platform](https://github.com/umi-soft/element-admin) 
+ 本工程模块介绍如下：  
  umi-spring-cloud-platform  
  |--- **umi-auth** ______ ***基于SSO、OAuth2.0的封装***  
  |--- **umi-common-utils** ______ ***通用工具类封装***  
  |--- **umi-config** ______ ***全局统一的配置***  
  |--- **umi-gateway** ______ ***网关***  
  |====== **umi-spring-cloud-gateway** ______ ***spring cloud gateway作为网关***  
  |--- **umi-micro-service** ______ ***微服务模块***  
  |====== **umi-system-admin** ______ ***通用系统管理服务***  
  |--- **umi-register-center** ______ ***注册中心***  
  |====== **umi-nacos** ______ ***阿里巴巴nacos注册中心***  
  |--- **umi-sentinel** ______ ***阿里巴巴限流组件***  
  |--- **umi-spring-boot-starters** ______ ***一些通用的spring boot starter***  
  |====== **umi-spring-boot-starter-redis** ______ ***redis key-value数据库***  
  |====== **umi-spring-boot-starter-ribbon** ______ ***ribbon负载均衡***  
  |====== **umi-spring-boot-starter-sentinel** ______ ***阿里巴巴sentinel限流客户端***  
  |====== **umi-spring-boot-starter-web** ______ ***微服务web通用快速启动配置***  
  |====== **umi-spring-boot-starter-web-jwt** ______ ***微服务jwt验证过滤限制***  

# 启动和运行
## 环境必备
+ redis、mysql、nacos、sentinel、IDE得支持lombok（不然代码各种报错）
+ 本工程的前端实现，[element-admin](https://github.com/umi-soft/element-admin) 使用umi-spring-cloud-platform分支
## 初始化
+ 执行umi-system-admin中的database/init.sql进行数据初始化
+ 配置好umi-config中[application.properties](https://github.com/umi-soft/umi-spring-cloud-platform/blob/dev/umi-config/src/main/resources/application.properties)的数据库配置
+ 配置好umi-config中[application.properties](https://github.com/umi-soft/umi-spring-cloud-platform/blob/dev/umi-config/src/main/resources/application.properties)的redis配置
+ 配置好umi-config中[application.properties](https://github.com/umi-soft/umi-spring-cloud-platform/blob/dev/umi-config/src/main/resources/bootstrap.yml)中的nacos、sentinel、gateway的配置
+ 配置好element-admin中[vue.config.js](https://github.com/umi-soft/element-admin/blob/umi-spring-cloud-platform/vue.config.js)的网关代理
## 按顺序运行
+ 运行你本地的nacos、sentinel
+ spring boot方式运行umi-spring-cloud-gateway
+ spring boot方式运行umi-system-admin
+ npm方式运行element-admin
