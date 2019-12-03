package com.cy.utils.designpattern.strategymode;

/**
 * created by cy on 2019/12/3 0003.
 * b、创建实体策略类
 */
public class ConcreteStrategy {

    public static class ShoppingStrategy implements IChaseStragety {

        @Override
        public void chase() {
            System.out.println("一起逛街咯~");
        }
    }

    public static class MoviesStrategy implements IChaseStragety {

        @Override
        public void chase() {
            System.out.println("一起看电影咯~");
        }
    }

    public static class EattingStrategy implements IChaseStragety {

        @Override
        public void chase() {
            System.out.println("一起吃饭咯~");
        }
    }

}
