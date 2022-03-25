package com.andy.readwrite;

import com.andy.exampleapi.ExampleExecuteTemplate;
import com.andy.exampleapi.service.ExampleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

@ComponentScan({"com.andy.examplemybatis","com.andy.readwrite"})
@MapperScan(basePackages = "com.andy.examplemybatis.repository")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class ReadwriteApplication {

    public static void main(String[] args) throws SQLException {

        //SpringApplication.run(ReadwriteApplication.class,args);

        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(ReadwriteApplication.class, args)) {
            ExampleExecuteTemplate.run(applicationContext.getBean(ExampleService.class));
        }
    }

}
