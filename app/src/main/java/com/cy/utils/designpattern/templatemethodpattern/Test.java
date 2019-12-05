package com.cy.utils.designpattern.templatemethodpattern;

/**
 * created by cy on 2019/12/5 0005.
 * <p>
 * a. 应用场景
 * <p>
 * 一次性实现算法的执行顺序和固定不变部分，可变部分则交由子类来实现。
 * 多个子类中拥有相同的行为时，可以将其抽取出来放在父类中，避免重复的代码。
 * 使用钩子方法来让子类决定父类的某个步骤是否执行，实现子类对父类的反向控制。
 * 控制子类扩展。模板方法只在特定点调用钩子方法，这样就只允许在这些点进行扩展。
 * <p>
 * b 优点
 * <p>
 * 提高代码复用性，去除子类中的重复代码。
 * 提高扩展性，不同实现细节放到不同子类中，易于增加新行为。
 * <p>
 * c. 缺点
 * <p>
 * 每个不同的实现都需要定义一个子类，这会导致类的个数的增加，设计更加抽象。
 * d. Android中的源码分析
 * <p>
 * Android中View的draw方法就是使用了模板方法模式：
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("----派送A----");
        Postman postA = new ConcretePostman.PostA();
        postA.post();
        System.out.println("----派送B----");
        Postman postB = new ConcretePostman.PostB();
        postB.post();
    }
}
