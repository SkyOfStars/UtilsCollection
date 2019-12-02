package com.cy.utils.designpattern.abstractfactorymode;

/**
 * created by cy on 2019/12/2 0002.
 */
public class IProduct {
    //抽象产品类-- CPU
    public static abstract class CPU {
        public abstract void showCPU();
    }
    //抽象产品类-- 内存
    public static abstract class Memory {
        public abstract void showMemory();
    }
    //抽象产品类-- 硬盘
    public static abstract class HD {
        public abstract void showHD();
    }
}
