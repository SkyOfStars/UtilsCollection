package com.cy.utils.designpattern.abstractfactorymode;

/**
 * created by cy on 2019/12/2 0002.
 * 抽象工厂类
 */
public abstract class ComputerFactory {
    public abstract IProduct.CPU createCPU();

    public abstract IProduct.Memory createMemory();

    public abstract IProduct.HD createHD();
}
