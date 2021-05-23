package com.example.springbootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * SpringBoot Actuator端点的实现原理 https://blog.csdn.net/reggergdsg/article/details/110534264
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class,args);

    }
}
