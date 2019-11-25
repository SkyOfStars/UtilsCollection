package com.cy.utils.designpattern.singletonmode;

/**
 * created by cy on 2019/11/25 0025.
 * 优点：懒加载，线程安全，推荐使用
 * <p>
 * 单例模式的总括：
 * <p>
 * a.应用场景
 * <p>
 * 频繁访问数据库或文件的对象。
 * 工具类对象；
 * 创建对象时耗时过多或耗费资源过多，但又经常用到的对象；
 * <p>
 * b.优点
 * <p>
 * 内存中只存在一个对象，节省了系统资源。
 * 避免对资源的多重占用，例如一个文件操作，由于只有一个实例存在内存中，避免对同一资源文件的同时操作。
 * <p>
 * c.缺点
 * <p>
 * 获取对象时不能用new
 * 单例对象如果持有Context，那么很容易引发内存泄露。
 * 单例模式一般没有接口，扩展很困难，若要扩展，只能修改代码来实现。
 */
public class Singleton {
    private Singleton() {
    }

    public static Singleton getInstance() {
        //第一次调用getInstance方法时才加载SingletonHolder并初始化sInstance
        return SingletonHolder.sInstance;
    }

    //静态内部类
    private static class SingletonHolder {
        private static final Singleton sInstance = new Singleton();
    }
}
