package com.cy.utils.designpattern.buildermode;

/**
 * created by cy on 2019/11/27 0027.
 * 定义具体的产品类（Product）：电脑
 */
public class Computer {
    private String mCPU;
    private String mMemory;
    private String mHD;

    public void setCPU(String CPU) {
        mCPU = CPU;
    }

    public void setMemory(String memory) {
        mMemory = memory;
    }

    public void setHD(String HD) {
        mHD = HD;
    }
}
