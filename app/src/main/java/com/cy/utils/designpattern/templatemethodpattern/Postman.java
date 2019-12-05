package com.cy.utils.designpattern.templatemethodpattern;

/**
 * created by cy on 2019/12/5 0005.
 * <p>
 * 1.定义
 * <p>
 * 定义一个操作中的算法框架，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结构即可重定义算法的某些特定步骤。
 * 2.介绍
 * <p>
 * 模板方法模式属于行为型模式。
 * 模板方法模式主要是用来定义一套流程下来的固定步骤，而具体的步骤实现则可以是不固定的。
 * <p>
 * 3.UML类图
 * 模板方法UML类图.jpg
 * 角色说明：
 * <p>
 * AbstractClass（抽象类）：，定义了一整套算法框架。
 * ConcreteClass（具体实现类）：具体实现类，根据需要去实现抽象类中的方法。
 * 4.实现
 * <p>
 * 继续以送快递为例，快递员送快递基本就是一套固定的流程：收到快递，准备派送->联系收货人->确定结果。
 * 4.1 创建抽象类
 * <p>
 * 定义算法框架，这里是快递员派送快递的步骤：
 */


public abstract class Postman {//抽象快递员类

    //派送流程
    public final void post() {//这里申明为final，不希望子类覆盖这个方法，防止更改流程的执行顺序
        prepare();//准备派送
        call();//联系收货人
        if (isSign())//是否签收
            sign();//签收
        else
            refuse();//拒签
    }

    protected void prepare() {//准备操作，固定流程，父类实现
        System.out.println("快递已达到，准备派送");
    }

    protected abstract void call();//联系收货人，联系人不一样，所以为抽象方法，子类实现

    protected boolean isSign() {//是否签收,这个是钩子方法，用来控制流程的走向
        return true;
    }

    protected void sign() {//签收，这个是固定流程，父类实现
        System.out.println("客户已签收，上报系统");
    }

    protected void refuse() {//拒签，空实现，这个也是钩子方法，子类可以跟进实际来决定是否去实现这个方法
    }

    /*
* 需要注意的是上面的抽象类（Postman）包含了三种类型的方法：抽象方法、具体方法和钩子方法。

    抽象方法：需要子类去实现。如上面的call()。
    具体方法：抽象父类中直接实现。如上面的prepare()和sign()。
    钩子方法：有两种，第一种，它是一个空实现的方法，子类可以视情况来决定是否要覆盖它，如上面的refuse()；第二种，它的返回类型通常是boolean类型的，一般用于对某个条件进行判断，如果条件满足则执行某一步骤，否则将不执行，如上面的isSign()。
* */
}

