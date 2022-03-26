package com.andy.shardingexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.andy.examplemybatis","com.andy.shardingexample"})
@MapperScan(basePackages = "com.andy.examplemybatis.repository")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class ShardingExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingExampleApplication.class, args);
	}

}
