package com.example.web.util;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedissonUtil {
    @Autowired
    private RedissonClient redisson;

    //操作Hash集合
    public void hadd(String key, String f, Object v) {
        redisson.getMap(key).put(f, v);
    }

    public boolean checkHKey(String key, String f) {
        return redisson.getMap(key).containsKey(f);
    }

    public boolean checkKey(String key) {
        return redisson.getKeys().countExists(key) > 0;
    }

    public String get(String key) {
        return (String) redisson.getBucket(key).get();
    }

    public Integer getInt(String key) {
        return (Integer) redisson.getBucket(key).get();
    }

    public boolean checkSet(String key, int id) {
        return redisson.getSet(key).contains(id);
    }

    public String getHashV(String key, String f) {
        return (String) redisson.getMap(key).get(f);
    }

    public int getHashVInt(String key, String f) {
        return (Integer) redisson.getMap(key).get(f);
    }

}
