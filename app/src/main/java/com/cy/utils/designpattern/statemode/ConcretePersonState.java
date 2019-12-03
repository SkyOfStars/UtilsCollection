package com.cy.utils.designpattern.statemode;

/**
 * created by cy on 2019/12/3 0003.
 * <p>
 * 2 创建具体状态类
 */
public class ConcretePersonState {
    public static class DogState implements PersonState {//单身狗状态

        @Override
        public void movies() {
            System.out.println("一个人偷偷看岛国大片");
        }

        @Override
        public void shopping() {
            //单身狗逛条毛街啊
            //空实现
        }
    }

    public static class LoveState implements PersonState {//恋爱状态

        @Override
        public void movies() {
            System.out.println("一起上电影院看大片~");
        }

        @Override
        public void shopping() {
            System.out.println("一起愉快的逛街去~");
        }
    }
}
