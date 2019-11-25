package com.cy.utils.designpattern.singletonmode;

/**
 * created by cy on 2019/11/25 0025.
 * 懒汉式
 * <p>
 * <p>
 * <p>
 * 优点：懒加载，线程安全，效率较高
 * 缺点：volatile影响一点性能，高并发下有一定的缺陷，某些情况下DCL会失效，虽然概率较小。
 */
public class Singleton4 {
    private volatile static Singleton4 singleton; //volatile 能够防止代码的重排序，保证得到的对象是初始化过

    private Singleton4() {
    }

    public static Singleton4 getSingleton() {
        if (singleton == null) {  //第一次检查，避免不必要的同步
            synchronized (Singleton4.class) {  //同步
                if (singleton == null) {   //第二次检查，为null时才创建实例
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}
