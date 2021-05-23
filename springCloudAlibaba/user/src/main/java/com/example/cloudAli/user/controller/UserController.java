package com.example.cloudAli.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.cloudAli.commonAli.utils.R;
import com.example.cloudAli.feignApi.OrderFeign;
import com.example.cloudAli.user.feign.OrderFeignService;
import com.example.cloudAli.user.sentinel.CommonBlockHandler;
import com.example.cloudAli.user.sentinel.CommonFallback;
import com.example.cloudAli.user.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    OrderFeignService orderFeignService;
    @Autowired
    private TestService testService;
    int count =0;

//    @SentinelResource(value = "findOrderByUserId",
//            blockHandlerClass = CommonBlockHandler.class,
//            blockHandler = "handleException2",
//            fallbackClass = CommonFallback.class,
//            fallback = "fallback")
    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R findOrderByUserId(@PathVariable("id") Integer id) {
        //feign调用
        R result = orderFeignService.findOrderByUserId(id);
        return result;
//        count++;

////        if(count%2==0){
//            int i=1/0;
////        }
//
////        testService.say();
//
//        return new R();
    }
}
