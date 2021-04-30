package com.example.jvm.spi;

import java.util.ServiceLoader;

public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<Car> cars = ServiceLoader.load(Car.class);
        for (Car car:cars) {
            System.out.println(car.getName());
        }
    }
}
