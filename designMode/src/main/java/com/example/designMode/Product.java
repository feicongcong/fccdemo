package com.example.designMode;

/**
 * 建造者模式
 */
public class Product {
    private final String part1;
    public Product(String part1 ) {
        this.part1=part1;

    }

    public String getPart1() {
        return part1;
    }

    static class Builder{
        private   String part1;
        public Builder builderPart1(String part1){
            this.part1=part1;
            return this;
        }
        Product build(){
            return new Product( part1 );
        }
    }
}
