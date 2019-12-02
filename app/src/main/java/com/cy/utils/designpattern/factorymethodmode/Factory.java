package com.cy.utils.designpattern.factorymethodmode;

/**
 * created by cy on 2019/12/2 0002.
 */
public abstract class Factory {
    public abstract Product create();
    //具体工厂类A
    public static class FactoryA extends Factory {
        @Override
        public Product create() {
            return new Product.ProductA();//创建ProductA
        }
    }
    //具体工厂类B
    public static class FactoryB extends Factory {
        @Override
        public Product create() {
            return new Product.ProductB();//创建ProductB
        }
    }
}
