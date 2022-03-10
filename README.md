# spring-cloud-2022



## 3. OpenFeign
### 3.1 Feign 是一个申明式Web服务客户端
集成了Eureka、Spring Cloud CircuitBreaker、Spring cloud LoadBalancer
### 3.2 如何在项目中添加Feign
1、添加 spring-cloud-starter-openfeign 依赖  
2、添加 @EnableFeignClients 注解

3、创建feignClient接口
### 3.3 @FeignClient 注解属性
1、value/name : 客户端名称,用于创建Spring Cloud LoadBalancer 客户端  
2、url: （绝对值或仅主机名）指定 URL  
3、decode404 ：是否应该解码 404 而不是抛出 FeignExceptions  
4、configuration： 自定义feign client ,包括 feign.codec.Decoder, feign.codec.Encoder, feign.Contract  
5、fallback  
6、fallbackFactory：  
7、日志
8、Feign Caching
9、常见应用程序属性

## 4. spring-cloud-loadbalancer
https://docs.spring.io/spring-cloud-commons/docs/current/reference/html/#spring-cloud-loadbalancer  
1、spring-cloud-eureka server and client start 包含了 spring-cloud-starter-loadbalancer  
### 4.1 负载均衡算法
1、默认是使用轮询算法，可以自定义

## 5. spring cloud Circuit Breader 
https://docs.spring.io/spring-cloud-commons/docs/current/reference/html/#spring-cloud-circuit-breaker  
spring cloud 现在支持3中 断路器（Resilience4J/Sentinel/Spring Retry）
### 5.1 sentinel 
https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D
1、Sentinel 已流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性
