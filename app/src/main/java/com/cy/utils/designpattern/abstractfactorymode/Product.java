package com.cy.utils.designpattern.abstractfactorymode;

/**
 * created by cy on 2019/12/2 0002.
 * 具体产品类
 */
public class Product {
    //具体产品类-- Intet CPU
    public static class IntelCPU extends IProduct.CPU {

        @Override
        public void showCPU() {
            System.out.println("Intet CPU");
        }
    }

    //具体产品类-- AMD CPU
    public static class AmdCPU extends IProduct.CPU {

        @Override
        public void showCPU() {
            System.out.println("AMD CPU");
        }
    }

    //具体产品类-- 三星 内存
    public static class SamsungMemory extends IProduct.Memory {

        @Override
        public void showMemory() {
            System.out.println("三星 内存");
        }
    }

    //具体产品类-- 金士顿 内存
    public static class KingstonMemory extends IProduct.Memory {

        @Override
        public void showMemory() {
            System.out.println("金士顿 内存");
        }
    }

    //具体产品类-- 希捷 硬盘
    public static class SeagateHD extends IProduct.HD {

        @Override
        public void showHD() {
            System.out.println("希捷 硬盘");
        }
    }

    //具体产品类-- 西部数据 硬盘
    public static class WdHD extends IProduct.HD {

        @Override
        public void showHD() {
            System.out.println("西部数据 硬盘");
        }
    }
}
