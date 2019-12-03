package com.cy.utils.designpattern.responsibilitychainmode;

/**
 * created by cy on 2019/12/3 0003.
 * <p>
 * 2 创建具体处理者类
 * <p>
 * 实现抽象处理者类中的方法：
 */
public class Handler {
    public static class BeijingPostman extends Postman {//北京快递员

        @Override
        public void handleCourier(String address) {
            if (address.equals("Beijing")) {//北京地区的则派送
                System.out.println("派送到北京");
                return;
            } else {//否则交给下一个快递员去处理
                nextPostman.handleCourier(address);
            }
        }
    }

    public static class ShanghaiPostman extends Postman {//上海快递员

        @Override
        public void handleCourier(String address) {
            if (address.equals("Shanghai")) {
                System.out.println("派送到上海");
                return;
            } else {
                nextPostman.handleCourier(address);
            }
        }
    }

    public static class GuangzhouPostman extends Postman {//广州快递员

        @Override
        public void handleCourier(String address) {
            if (address.equals("Guangzhou")) {
                System.out.println("派送到广州");
                return;
            } else {
                if (nextPostman != null)
                    nextPostman.handleCourier(address);
            }
        }
    }

}
