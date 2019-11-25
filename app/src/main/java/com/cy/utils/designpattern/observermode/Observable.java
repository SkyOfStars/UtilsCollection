package com.cy.utils.designpattern.observermode;

/**
 * created by cy on 2019/11/25 0025.
 */
public interface Observable {//抽象被观察者

    void add(Observer observer);//添加观察者

    void remove(Observer observer);//删除观察者

    void notify(String message);//通知观察者
}
