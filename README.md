# spring-cloud-2022

eureka
https://cloud.spring.io/spring-cloud-netflix/reference/html/

## 1.Eureka 客户端

### 1.1 如何在项目中添加 Eureka Client
1、添加 spring-cloud-starter-netflix-eureka-client 依赖
2、添加 eureka.client.serviceUrl.defaultZone: 服务器路径

### 1.2 EurekaInstanceConfigBean（"eureka.instance"）
1、leaseRenewalIntervalInSeconds： 指示 eureka 客户端需要多长时间（以秒为单位）向 eureka 服务器发送心跳以指示它仍然处于活动状态，默认30秒
2、leaseExpirationDurationInSeconds： 指示 eureka 服务器在收到最后一次心跳后等待的时间（以秒为单位），然后才能从视图中删除此实例，并通过禁止到此实例的流量来移除此实例，默认时间 90秒

### 1.3 EurekaClientConfigBean（"eureka.client"）
1、registryFetchIntervalSeconds：指示从 eureka 服务器获取注册表信息的频率（以秒为单位，默认30

### 1.4 Eureka 的健康检查

### 1.5 Eureka 元数据
eureka.instance.metadataMap

## 2.Eureka 服务端
### 2.1 如何在项目中添加Eureka Server
1、添加 spring-cloud-starter-netflix-eureka-server 依赖
2、添加 @EnableEurekaServer注解
3、添加配置属性
### 2.2 EurekaServerConfigBean（eureka.server）
1、enableSelfPreservation：启用自我保护 默认true
2、renewalPercentThreshold：续约百分比阈值 默认 0.85
3、eviction-interval-timer-in-ms：逐出过期服务间隔时间，默认60s

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


