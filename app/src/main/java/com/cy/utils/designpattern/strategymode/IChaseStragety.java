package com.cy.utils.designpattern.strategymode;

/**
 * created by cy on 2019/12/3 0003.
 * 1、
 * Stragety(抽象策略类)：抽象类或接口，提供具体策略类需要实现的接口。
 * ConcreteStragetyA、ConcreteStragetyB（具体策略类）：具体的策略实现，封装了相关的算法实现。
 * Context（环境类）：用来操作策略的上下文环境。
 * <p>
 * a、创建抽象策略类
 */
public interface  IChaseStragety { //追求策略
    void chase();//抽象追求方法
}
