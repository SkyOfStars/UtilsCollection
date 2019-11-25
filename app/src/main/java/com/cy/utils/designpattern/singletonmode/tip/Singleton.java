package com.cy.utils.designpattern.singletonmode.tip;

import com.cy.utils.designpattern.singletonmode.Singleton1;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * created by cy on 2019/11/25 0025.
 * <p>
 * Constructor con = Singleton.class.getDeclaredConstructor();
 * con.setAccessible(true);
 * // 通过反射获取实例
 * Singleton singeton1 = (Singleton) con.newInstance();
 * Singleton singeton2 = (Singleton) con.newInstance();
 * System.out.println(singeton1==singeton2);//结果为false,singeton1和singeton2将是两个不同的实例
 * <p>
 * <p>
 * 可以通过当第二次调用构造函数时抛出异常来防止反射破坏单例，以懒汉式为例：
 */
public class Singleton {
    private static boolean flag = true;
    private static Singleton single = null;

    private Singleton() {
        if (flag) {
            flag = !flag;
        } else {
            throw new RuntimeException("单例模式被破坏！");
        }
    }

    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }


    /**
     * 反序列化时也会破坏单例模式，可以通过重写readResolve方法避免，以饿汉式为例：
     */
    public static class Singleton1 implements Serializable {
        private Singleton1() {
        }

        private static final Singleton1 single = new Singleton1();

        public static Singleton1 getInstance() {
            return single;
        }

        private Object readResolve() throws ObjectStreamException {//重写readResolve()
            return single;//直接返回单例对象
        }
    }
}
