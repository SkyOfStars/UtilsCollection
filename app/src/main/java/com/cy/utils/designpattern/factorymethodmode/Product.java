package com.cy.utils.designpattern.factorymethodmode;

/**
 * created by cy on 2019/12/2 0002.
 */
//抽象产品类
public abstract class Product {
    public abstract void show();
    public static class ProductA extends Product {
        @Override
        public void show() {
            System.out.println("product A");
        }
    }
    //具体产品类B
    public static class ProductB extends Product {
        @Override
        public void show() {
            System.out.println("product B");
        }
    }
}
