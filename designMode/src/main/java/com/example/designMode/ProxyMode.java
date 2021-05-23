package com.example.designMode;

public class ProxyMode {
    public static void main(String[] args) {
        new ProxyClass().operate();
    }
}

class Target implements TargetInterface {
    @Override
    public void operate() {
        System.out.println("operate");
    }
}

class ProxyClass implements TargetInterface {
    private TargetInterface Target = new Target();

    @Override
    public void operate() {
        Target.operate();
    }
}

interface TargetInterface {
    void operate();
}