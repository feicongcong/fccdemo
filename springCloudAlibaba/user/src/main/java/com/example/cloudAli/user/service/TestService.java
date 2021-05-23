package com.example.cloudAli.user.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.cloudAli.user.sentinel.CommonBlockHandler;
import com.example.cloudAli.user.sentinel.CommonFallback;
import org.springframework.stereotype.Service;

@Service
public class TestService {
     int count =0;

    @SentinelResource(value = "say")
//    @SentinelResource(value = "say",
//            blockHandlerClass = CommonBlockHandler.class,
//            blockHandler = "handleException2",
//            fallbackClass = CommonFallback.class,
//            fallback = "fallback")
    public void say(){
         count++;

         if(count%2==0){
             int i=1/0;
         }
     }
}
