package com.cy.utils.designpattern.prototypemode;

/**
 * created by cy on 2019/12/3 0003.
 * <p>
 * 实现深拷贝，这就需要在Card的clone方法中，将源对象引用的Spec对象也clone一份。
 */
public class Card1 implements Cloneable {
    private int num;

    private Spec1 spec = new Spec1();

    public Card1() {
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
    protected Card1 clone() throws CloneNotSupportedException {
        System.out.println("clone时不执行构造函数");
        Card1 card = (Card1) super.clone();
        card.spec = (Spec1) spec.clone();//对spec对象也调用clone，实现深拷贝
        return card;
    }

    public static class Spec1 implements Cloneable {//Spec也实现Cloneable接口
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

        @Override
        protected Spec1 clone() throws CloneNotSupportedException {//重写Spec的clone方法
            return (Spec1) super.clone();
        }
    }


}

