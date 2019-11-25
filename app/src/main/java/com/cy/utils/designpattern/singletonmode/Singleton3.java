package com.cy.utils.designpattern.singletonmode;

/**
 * created by cy on 2019/11/25 0025.
 * 懒汉式
 * <p>
 * <p>
 * <p>
 * 优点：实现了懒加载的效果，线程安全。
 * 缺点：使用synchronized会造成不必要的同步开销，而且大部分时候我们是用不到同步的。
 */
public class Singleton3 {
    private Singleton3() {
    }

    private static Singleton3 single = null;

    public static synchronized Singleton3 getInstance() { //加上synchronized同步
        if (single == null) {
            single = new Singleton3();
        }
        return single;
    }
}
