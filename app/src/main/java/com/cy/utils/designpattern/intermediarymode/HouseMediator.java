package com.cy.utils.designpattern.intermediarymode;

/**
 * created by cy on 2019/12/5 0005.
 *
 * 3 创建抽象中介者角色
 */
public interface HouseMediator {//房屋中介类
    void notice(Person person, String msg);//通知方法
}
