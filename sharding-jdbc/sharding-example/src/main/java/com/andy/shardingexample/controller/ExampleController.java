package com.andy.shardingexample.controller;

import com.andy.exampleapi.ExampleExecuteTemplate;
import com.andy.exampleapi.repository.OrderRepository;
import com.andy.exampleapi.repository.UserRepository;
import com.andy.exampleapi.service.ExampleService;
import com.andy.examplemybatis.service.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;

@RestController
public class ExampleController {

    @Resource
    private ExampleService exampleService;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private UserServiceImpl userService ;

    @GetMapping("orderTest")
    public void orderTest() throws SQLException {
        ExampleExecuteTemplate.run(exampleService);
    }

    @GetMapping("getOrder")
    public Object getOrder() throws SQLException {
        return orderRepository.selectAll();
    }

    @GetMapping("userTest")
    public void userTest() throws SQLException {
        ExampleExecuteTemplate.run(userService);
    }
}
