package com.andy.readwrite.controller;


import com.andy.exampleapi.repository.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;

@RestController
public class ExampleController {

    @Resource
    private OrderRepository orderRepository;


    /**
     * 验证查询都是走读库
     * @return
     * @throws SQLException
     */
    @GetMapping("findOrder")
    public Object findOrder() throws SQLException {
        return orderRepository.selectAll();
    }
}
