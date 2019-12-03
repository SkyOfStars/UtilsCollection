package com.cy.utils.designpattern.prototypemode;

/**
 * created by cy on 2019/12/3 0003.
 * <p>
 * 3.1 角色说明：
 * <p>
 * Prototype（抽象原型类）：抽象类或者接口，用来声明clone方法。
 * ConcretePrototype1、ConcretePrototype2（具体原型类）：即要被复制的对象。
 * Client（客户端类）：即要使用原型模式的地方。
 * <p>
 * 4.实现
 * 4.1 Prototype（抽象原型类）
 * <p>
 * 通常情况下，Prototype是不需要我们去定义的。因为拷贝这个操作十分常用，Java中提供了Cloneable接口来支持拷贝操作，它就是原型模式中的Prototype。
 * 当然，原型模式也未必非得去实现Cloneable接口，也有其他的实现方式。
 * <p>
 * 4.2 创建具体原型类
 * <p>
 * 浅拷贝
 */
//具体原型类,卡片类
public class Card implements Cloneable {//实现Cloneable接口，Cloneable只是标识接口
    private int num;//卡号

    private Spec spec = new Spec();//卡规格

    public Card() {
        System.out.println("Card 执行构造函数");
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSpec(int length, int width) {
        spec.setLength(length);
        spec.setWidth(width);
    }

    @Override
    public String toString() {
        return "Card{" +
                "num=" + num +
                ", spec=" + spec +
                '}';
    }

    @Override
    protected Card clone() throws CloneNotSupportedException {//重写clone()方法，clone()方法不是Cloneable接口里面的，而是Object里面的
        System.out.println("clone时不执行构造函数");
        return (Card) super.clone();
    }

    //规格类，有长和宽这两个属性
    public static class Spec {
        private int width;
        private int length;

        public void setLength(int length) {
            this.length = length;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public String toString() {
            return "Spec{" +
                    "width=" + width +
                    ", length=" + length +
                    '}';
        }
    }

}

