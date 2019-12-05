package com.cy.utils.designpattern.intermediarymode;

/**
 * created by cy on 2019/12/5 0005.
 */
public class ConcretePerson {
    public static class Purchaser extends Person {//买房者类，继承Person

        public Purchaser(HouseMediator houseMediator) {
            super(houseMediator);
        }

        @Override
        public void send(String message) {
            System.out.println("买房者发布信息：" + message);
            houseMediator.notice(this, message);
        }

        @Override
        public void getNotice(String message) {
            System.out.println("买房者收到消息：" + message);
        }
    }

    public static class Landlord extends Person {//房东者类，继承Person

        public Landlord(HouseMediator houseMediator) {
            super(houseMediator);
        }

        @Override
        public void send(String message) {
            System.out.println("房东发布信息：" + message);
            houseMediator.notice(this, message);
        }

        @Override
        public void getNotice(String message) {
            System.out.println("房东收到消息：" + message);
        }
    }
}
