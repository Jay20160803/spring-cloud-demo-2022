package com.andy.eurekaconsumer.hello;

import com.andy.eurekaconsumer.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * Created time on 2022/3/8 11:17
 * @description hello controller
 */
@RestController
public class HelloController {

    @Autowired
    private Client client;

    @GetMapping("hello")
    public String hello(){
        return client.hello();
    }
}
