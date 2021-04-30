package com.example.shardingSphere;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

public class Test {
    public static void main(String[] args) {
        System.out.println(new SnowflakeShardingKeyGenerator().generateKey());
    }
}
