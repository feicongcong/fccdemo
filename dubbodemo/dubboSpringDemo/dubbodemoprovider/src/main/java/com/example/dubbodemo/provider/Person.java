package com.example.dubbodemo.provider;

import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Person {
    String getName();
}
