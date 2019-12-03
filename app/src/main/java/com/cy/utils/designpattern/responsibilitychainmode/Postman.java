package com.cy.utils.designpattern.responsibilitychainmode;

/**
 * created by cy on 2019/12/3 0003.
 * 责任链模式
 * <p>
 * 角色说明：
 * <p>
 * Handler（抽象处理者）：抽象类或者接口,定义处理请求的方法以及持有下一个Handler的引用.
 * ConcreteHandler1,ConcreteHandler2（具体处理者）：实现抽象处理类,对请求进行处理,如果不处理则转发给下一个处理者.
 * Client (客户端):即要使用责任链模式的地方
 * <p>
 * 1 创建抽象处理者类
 * <p>
 * 定义处理请求的方法以及持有下一个Handler的引用：
 */
public abstract class Postman {//快递员抽象类
    protected Postman nextPostman;//下一个快递员

    public abstract void handleCourier(String address);//派送快递
}
