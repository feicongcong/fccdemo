package com.example.cloudAli.order.controller;

import com.example.cloudAli.commonAli.utils.R;
import com.example.cloudAli.order.entity.OrderEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    /**
     * 根据用户id查询订单信息
     * @param userId
     * @return
     */
    @RequestMapping("/findOrderByUserId/{userId}")
    public R findOrderByUserId(@PathVariable("userId") Integer userId) {

//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //模拟异常
//        if(userId==5){
//            throw new IllegalArgumentException("非法参数异常");
//        }

        log.info("根据userId:"+userId+"查询订单信息");
//        List<OrderEntity> orderEntities = orderService.listByUserId(userId);
        OrderEntity orderEntity = new OrderEntity();
        List<OrderEntity> orderEntities = Lists.newArrayList(orderEntity);
        return R.ok().put("orders", orderEntities);
    }
}
