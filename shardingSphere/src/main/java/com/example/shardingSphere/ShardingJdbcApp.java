package com.example.shardingSphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.shardingSphere.mapper")
@SpringBootApplication
public class ShardingJdbcApp {
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApp.class,args);
    }
}
