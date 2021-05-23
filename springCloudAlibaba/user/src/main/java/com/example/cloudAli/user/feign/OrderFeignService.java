package com.example.cloudAli.user.feign;

import com.example.cloudAli.commonAli.utils.R;
import com.example.cloudAli.feignApi.OrderFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "order",path="/order",fallback = FallbackOrderFeignService.class)
public interface OrderFeignService extends OrderFeign {

    @RequestMapping("/findOrderByUserId/{userId}")
    R findOrderByUserId(@PathVariable("userId") Integer userId);
}
