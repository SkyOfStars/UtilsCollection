package com.cy.utils.designpattern.factorymethodmode;

/**
 * created by cy on 2019/12/2 0002.
 * <p>
 * <p>
 * <p>
 * 工厂方法模式属于创建型模式。
 * 工厂方法模式主要用来创建复杂的对象，简单对象能够使用new来创建就不用工厂方法模式来创建了。
 * <p>
 * <p>
 * a.应用场景
 * <p>
 * 生成复杂对象时，无需知道具体类名，只需知道相应的工厂方法即可。
 * <p>
 * b.优点
 * <p>
 * 符合开放封闭原则。新增产品时，只需增加相应的具体产品类和相应的工厂子类即可。
 * 符合单一职责原则。每个具体工厂类只负责创建对应的产品。
 * <p>
 * c.缺点
 * <p>
 * 一个具体工厂只能创建一种具体产品。
 * 增加新产品时，还需增加相应的工厂类，系统类的个数将成对增加，增加了系统的复杂度和性能开销。
 * 引入的抽象类也会导致类结构的复杂化。
 * <p>
 * Android中的源码分析
 * <p>
 * Android中的ThreadFactory就是使用了工厂方法模式来生成线程的，线程就是ThreadFactory的产品
 */
public class Test {

    public static void main(String[] args) {
        //产品A
        Factory factoryA = new Factory.FactoryA();
        Product productA = factoryA.create();
        productA.show();
        //产品B
        Factory factoryB = new Factory.FactoryB();
        Product productB = factoryB.create();
        productB.show();
    }
}
