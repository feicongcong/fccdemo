package com.example.dubbodemo.provider;


import com.example.dubbodemo.api.ProviderServiceInterface;
import com.example.dubbodemo.api.User;
import org.apache.dubbo.config.annotation.Service;

@Service
public class ProviderService implements ProviderServiceInterface {

    public User getUser() {
        return new User("周瑜");
    }
}
