package com.cy.utils.designpattern.observermode;

/**
 * created by cy on 2019/11/25 0025.
 */
public class Boy implements Observer {//观察者
    private String name;//名字
    public Boy(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {//男孩的具体反应
        System.out.println(name + ",收到了信息:" + message+"屁颠颠的去取快递.");
    }
}
