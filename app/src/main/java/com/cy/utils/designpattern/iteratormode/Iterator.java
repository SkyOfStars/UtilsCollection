package com.cy.utils.designpattern.iteratormode;

/**
 * created by cy on 2019/12/5 0005.
 * <p>
 * 定义
 * <p>
 * 提供一种方法访问一个容器对象中各个元素，而又不需暴露该对象的内部细节。
 * 2.介绍
 * <p>
 * 迭代器模式属于行为型模式。
 * 迭代器（Iterator）模式，又叫做游标（Cursor）模式。
 * Java中的Map、List等等容器，都使用到了迭代器模式。
 * <p>
 * 3.UML类图
 * 迭代器模式UML类图.jpg
 * 角色说明：
 * <p>
 * Iterator（迭代器接口）：负责定义、访问和遍历元素的接口。
 * ConcreteIterator（具体迭代器类）:实现迭代器接口。
 * Aggregate（容器接口）：定义容器的基本功能以及提供创建迭代器的接口。
 * ConcreteAggregate（具体容器类）：实现容器接口中的功能。
 * Client（客户端类）：即要使用迭代器模式的地方。
 * <p>
 * 4.实现
 * <p>
 * 继续以快递举例子，货架上有很多快递包，取快递时我们报出手机号，然后快递小哥就遍历货架上所有的快递信息，直到找出快递包为止：
 * 4.1 创建迭代器接口
 */
public interface Iterator {

    boolean hasNext();    //是否存在下一条记录

    Object next();        //返回当前记录并移到下一条记录
}

