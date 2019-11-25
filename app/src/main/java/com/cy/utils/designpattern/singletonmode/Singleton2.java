package com.cy.utils.designpattern.singletonmode;

/**
 * created by cy on 2019/11/25 0025.
 * 懒汉式
 * <p>
 * <p>
 * 优点：实现了懒加载的效果。
 * 缺点：线程不安全。
 */
public class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 single = null;

    public static Singleton2 getInstance() {
        if (single == null) {
            single = new Singleton2();  //在第一次调用getInstance()时才实例化，实现懒加载,所以叫懒汉式
        }
        return single;
    }
}
