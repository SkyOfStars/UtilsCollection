package com.cy.utils.designpattern.prototypemode;

/**
 * created by cy on 2019/12/3 0003.
 * 创建客户端类
 * 浅拷贝测试
 * <p>
 * <p>
 * a. 应用场景
 * <p>
 * 如果初始化一个类时需要耗费较多的资源，比如数据、硬件等等，可以使用原型拷贝来避免这些消耗。
 * 通过new创建一个新对象时如果需要非常繁琐的数据准备或者访问权限，那么也可以使用原型模式。
 * 一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以拷贝多个对象供调用者使用，即保护性拷贝。
 * <p>
 * b. 优点
 * <p>
 * 可以解决复杂对象创建时消耗过多的问题，在某些场景下提升创建对象的效率。
 * 保护性拷贝，可以防止外部调用者对对象的修改，保证这个对象是只读的。
 * <p>
 * c. 缺点
 * <p>
 * 拷贝对象时不会执行构造函数。
 * 有时需要考虑深拷贝和浅拷贝的问题。
 */
public class Client {
    public static void main(String[] args) {
        try {
            test();
            //            test2();
            //            test3();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 浅拷贝测试1
     *
     * @throws CloneNotSupportedException
     */
    public static void test() throws CloneNotSupportedException {

        Card card1 = new Card();
        card1.setNum(9527);
        card1.setSpec(10, 20);
        System.out.println(card1.toString());
        System.out.println("----------------------");

        Card card2 = card1.clone();
        System.out.println(card2.toString());
        System.out.println("----------------------");
    }

    /**
     * 浅拷贝测试2
     * clone对象时不会执行构造函数
     * clone方法不是Cloneable接口中的，而是Object中的方法。Cloneable是个标识接口，表面了这个对象是可以拷贝的，如果没有实现Cloneable接口却调用clone方法则会报错。
     *
     * @throws CloneNotSupportedException
     */
    public static void test2() throws CloneNotSupportedException {
        Card card1 = new Card();
        card1.setNum(9527);
        card1.setSpec(10, 20);
        System.out.println(card1.toString());
        System.out.println("----------------------");

        Card card2 = card1.clone();
        System.out.println(card2.toString());
        System.out.println("----------------------");

        card2.setNum(7259);
        System.out.println(card1.toString());
        System.out.println(card2.toString());
        System.out.println("----------------------");

        card2.setSpec(30, 40);
        System.out.println(card1.toString());
        System.out.println(card2.toString());
        System.out.println("----------------------");
    }

    /**
     * 深度拷贝测试
     *
     * @throws CloneNotSupportedException
     */
    public static void test3() throws CloneNotSupportedException {
        Card1 card1 = new Card1();
        card1.setNum(9527);
        card1.setSpec(10, 20);
        System.out.println(card1.toString());
        System.out.println("----------------------");

        Card1 card2 = card1.clone();
        System.out.println(card2.toString());
        System.out.println("----------------------");

        card2.setNum(7259);
        System.out.println(card1.toString());
        System.out.println(card2.toString());
        System.out.println("----------------------");

        card2.setSpec(30, 40);
        System.out.println(card1.toString());
        System.out.println(card2.toString());
        System.out.println("----------------------");
    }

}
