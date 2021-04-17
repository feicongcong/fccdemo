package com.example.cloudAli.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestConfig {
//    @Autowired
//    LoadBalancerClient loadBalancer;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();



//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(Collections.singletonList(new LoadBalancerInterceptor(loadBalancer)));
//        return restTemplate;
    }
}
