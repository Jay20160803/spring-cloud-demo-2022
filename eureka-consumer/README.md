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

### 7、流控方式
7.1 直接拒绝（默认方式）：适用于对系统处理能力确切已知情况下
7.2 Warm Up: 预热/冷启动：让通过的流量缓慢增加，在一定时间内逐渐增加到阈值上限，给冷系统一个预热的时间，避免冷系统被压垮  
    算法： 令牌桶算法，系统每秒在桶中添加b个令牌，系统每处理一个请求就从桶中获取一个令牌  
7.3 匀速排队：严格控制请求通过的间隔时间，适用于间隔性突发的流量（暂不支持 QPS > 1000 的场景）  
    算法： 漏桶算法，当前请求距离上个通过的请求的时间大于预设值，则当前请求通过；  
    否则计算当前请求的预期通过时间，如果预期通过时间大于timeout时间则请求会等待；否则直接拒绝这个请求  
### 8、一个资源设置多个流控规则时
