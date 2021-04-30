package com.example.dubbodemo.consumer;

import com.alibaba.fastjson.JSONObject;
import com.example.dubbodemo.api.ProviderServiceInterface;
import com.example.dubbodemo.api.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Reference
    private ProviderServiceInterface providerService;

    public String test() {
//        String result = HttpClient.get("http://localhost:8080/provider/service");
//        User user = JSONObject.parseObject(result, User.class);
//        return user.getUsername();
        return providerService.getUser().getUsername();
    }
}
