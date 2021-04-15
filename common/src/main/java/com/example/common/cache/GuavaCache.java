package com.example.common.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/why_still_confused/article/details/107138230
 */
public class GuavaCache {
    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .initialCapacity(10)
                .maximumSize(10)
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build();

        for (int i = 1; i <= 10; i++) {
            cache.put(i + "", i + "");
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println(cache.getIfPresent(i + ""));
        }
        Thread.sleep(1000 * 6);
        System.out.println(cache.asMap());
    }
}
