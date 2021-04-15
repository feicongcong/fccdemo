package com.example.web.controller;

import com.example.web.entity.User;
import com.example.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
//    @Autowired
//    private RedissonClient redissonClient;
//    @Autowired
//    private Redisson redisson;
//    @Autowired
//    private RedissonUtil redissonUtil;

    @GetMapping("get/{id}")
    public User get(@PathVariable("id") Integer id) {
//        RKeys keys = redissonClient.getKeys();
//        System.out.println(keys);
//        redisson.getk
//        RBucket<Integer> age = redissonClient.getBucket("age");
//        age.set(28);
//        RBucket<Integer> age1 = redissonClient.getBucket("age");
//        System.out.println(age1.get());

//        System.out.println(redissonUtil.get("name"));
//        System.out.println(redissonUtil.getInt("age"));
        return userMapper.getById(1);
    }


}
