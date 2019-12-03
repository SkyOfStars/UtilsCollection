package com.cy.utils.designpattern.statemode;

/**
 * created by cy on 2019/12/3 0003.
 * <p>
 * State（抽象状态角色）：抽象类或者接口，定义对象的各种状态和行为。
 * ConcreteState（具体状态角色）：实现抽象角色类，定义了本状态下的行为，即要做的事情。
 * Context（环境角色）：定义客户端需要的接口，并且负责具体状态的切换。
 *
 * 1 创建抽象状态类
 */
public interface PersonState {
    void movies();//看电影

    void shopping();//逛街
}
