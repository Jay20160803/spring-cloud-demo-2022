eureka client + openfeign + spring cloud loadBalance + sentinel

### 1、Sentinel core
https://github.com/alibaba/spring-cloud-alibaba/blob/2.2.x/spring-cloud-alibaba-examples/sentinel-example/sentinel-core-example/readme-zh.md  

1、添加依赖  

2、接入限流埋点  
2.1 HTTP 埋点：Sentinel starter 默认为所有HTTP服务提供了限流埋点  
2.2 自定义埋点：如果需要对某个特定方法进行限流或降级，可以通过@SentinelResource注解来完成限流的埋点  

3、配置限流规则  
3.1 sentinel 提供了两种限流配置规则的方式： 代码配置和控制台配置  

### 2、Sentinel dashboard
1、下载jar或源码启动项目
2、项目配置Sentinel dashboard地址

### 3、自定义限流处理逻辑
3.1 默认限流异常处理  DefaultBlockExceptionHandler  
可实现BlockExceptionHandler 自定义  
3.2 使用@SentinelResource注解下的限流异常处理  

### 4、Endpoint信息查看

### 5、ReadableDataSource 支持

### 6、熔断降级
1、添加注解 @SentinelResource  
2、设置DegradeRule  
