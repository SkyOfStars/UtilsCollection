package com.cy.utils.designpattern.intermediarymode;

/**
 * created by  on 2019/12/5 0005.
 * <p>
 * 1.定义
 * <p>
 * 用一个中介者对象来封装一系列的对象交互。中介者使得各对象不需要显式地相互引用，从而使其松散耦合，而且可以独立地改变它们之间的交互。
 * 2.介绍
 * <p>
 * 中介者模式属于行为型模式。
 * 中介者模式也称为调解者模式或者调停者模式。
 * <p>
 * 当程序存在大量的类时，多个对象之间存在着依赖的关系，呈现出网状结构，那么程序的可读性和可维护性就变差了，并且修改一个类需要牵涉到其他类，不符合开闭原则。
 * <p>
 * 网状结构.jpg
 * <p>
 * 因此我们可以引入中介者，将网状结构转化成星型结构，可以降低程序的复杂性，并且可以减少各个对象之间的耦合。
 * <p>
 * 星型结构.jpg
 * <p>
 * 3.UML类图
 * 中介者模式UML类图.jpg
 * 角色说明：
 * <p>
 * Mediator(抽象中介者角色):抽象类或者接口,定义统一的接口，用于各同事角色之间的通信。
 * ConcreteMediator(具体中介者角色):继承或者实现了抽象中介者，实现了父类定义的方法,协调各个具体同事进行通信。
 * Colleague(抽象同事角色):抽象类或者接口,定义统一的接口，它只知道中介者而不知道其他同事对象。
 * ConcreteColleague(具体同事角色):继承或者实现了抽象同事角色，每个具体同事类都知道自己本身的行为，其他的行为只能通过中介者去进行。
 *
 * <p>
 * 1 、创建抽象同事角色
 * <p>
 * 无论是房东还是购房者，他们都能够发布信息和接受信息：
 */
public abstract class Person {//人物类
    protected HouseMediator houseMediator;

    public Person(HouseMediator houseMediator) {
        this.houseMediator = houseMediator;//获取中介
    }

    public abstract void send(String message);//发布信息

    public abstract void getNotice(String message);//接受信息
}
