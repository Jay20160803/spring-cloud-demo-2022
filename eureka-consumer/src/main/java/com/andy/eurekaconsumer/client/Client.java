package com.andy.eurekaconsumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Andy
 * Created time on 2022/3/8 11:14
 * @description Client 客户端
 */
@FeignClient("client")
public interface Client {

    @GetMapping("/hello")
    public String hello();
}
