package com.andy.eurekaconsumer.hello;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.andy.eurekaconsumer.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author Andy
 * Created time on 2022/3/8 11:17
 * @description hello controller
 */
@Slf4j
@RestController
public class HelloController {

    @Autowired
    private Client client;

    @GetMapping("hello")
    @SentinelResource(value = "helloDegrade",fallback= "hellFallBack")
    public String hello(){

        try {
            int randomInt = RandomUtils.nextInt(100);
            log.info("randomInt = " + randomInt);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return client.hello();
    }

    public String hellFallBack(Throwable ex){
        log.error("ex,{}",ex);
        return "hellFallBack";
    }
}
