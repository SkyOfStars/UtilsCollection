package com.cy.utils.designpattern.strategymode;

/**
 * created by cy on 2019/12/3 0003.
 * 用来操作不同的策略
 */
public class Context {
    private IChaseStragety chaseStragety;//定义抽象策略类

    public Context(IChaseStragety chaseStragety) {//构造方法传递具体策略对象过来
        this.chaseStragety = chaseStragety;
    }

    public void chase() {//执行具体策略对象的策略
        chaseStragety.chase();
    }
}
