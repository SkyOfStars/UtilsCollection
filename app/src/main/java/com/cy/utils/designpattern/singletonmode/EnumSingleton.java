package com.cy.utils.designpattern.singletonmode;

/**
 * created by cy on 2019/11/25 0025.
 * <p>
 * 枚举单例
 * <p>
 * 优点：线程安全，写法简单，能防止反序列化重新创建新的对象。
 * 缺点：可读性不高，枚举会比静态常量多那么一丁点的内存。
 */
public enum EnumSingleton {

    INSTANCE;   //定义一个枚举的元素，它就是Singleton的一个实例

    public void doSomething() {
    }
}
