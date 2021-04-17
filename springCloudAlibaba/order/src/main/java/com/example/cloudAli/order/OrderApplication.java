package com.example.cloudAli.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
//        , DruidDataSourceAutoConfigure.class
})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(
                OrderApplication.class,args
        );
    }
}
