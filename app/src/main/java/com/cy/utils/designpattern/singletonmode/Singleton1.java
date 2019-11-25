package com.cy.utils.designpattern.singletonmode;

/**
 * created by cy on 2019/11/25 0025.
 * 单例类.
 *
 *
 优点：写法简单，线程安全。
 缺点：没有懒加载的效果，如果没有使用过的话会造成内存浪费。

 */
public class Singleton1 {

    private Singleton1() {//构造方法为private,防止外部代码直接通过new来构造多个对象
    }

    private static final Singleton1 single = new Singleton1();  //在类初始化时，已经自行实例化,所以是线程安全的。

    public static Singleton1 getInstance() {  //通过getInstance()方法获取实例对象
        return single;
    }
}
