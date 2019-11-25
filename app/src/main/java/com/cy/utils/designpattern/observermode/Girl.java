package com.cy.utils.designpattern.observermode;

/**
 * created by cy on 2019/11/25 0025.
 */
public class Girl implements Observer {//观察者
    private String name;//名字
    public Girl(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {//女孩的具体反应
        System.out.println(name + ",收到了信息:" + message+"让男朋友去取快递~");
    }
}
