package com.cy.utils.designpattern.simplefactorymode;

import com.cy.utils.designpattern.factorymethodmode.Product;

/**
 * created by cy on 2019/12/2 0002.
 * <p>
 * <p>
 * 简单工厂模式属于创建型模式。
 * 简单工厂模式又叫做静态工厂方法模式。
 * <p>
 * a.应用场景
 * <p>
 * 生成复杂对象时，确定只有一个工厂类，可以使用简单工厂模式。否则有多个工厂类的话，使用工厂方法模式。
 * <p>
 * b.优点
 * <p>
 * 代码解耦，创建实例的工作与使用实例的工作分开，使用者不必关心类对象如何创建。
 * <p>
 * c.缺点
 * <p>
 * 违背开放封闭原则，若需添加新产品则必须修改工厂类逻辑，会造成工厂逻辑过于复杂。
 * 简单工厂模式使用了静态工厂方法，因此静态方法不能被继承和重写。
 * 工厂类包含了所有实例（产品）的创建逻辑，若工厂类出错，则会造成整个系统都会会受到影响。
 * <p>
 * d.工厂方法模式与简单工厂模式比较
 * <p>
 * 工厂方法模式有抽象工厂类，简单工厂模式没有抽象工厂类且其工厂类的工厂方法是静态的。
 * 工厂方法模式新增产品时只需新建一个工厂类即可，符合开放封闭原则；而简单工厂模式需要直接修改工厂类，违反了开放封闭原则。
 */
public class Factory {
    public static Product create(String productName) {
        Product product = null;
        //通过switch语句控制生产哪种商品
        switch (productName) {
            case "A":
                product = new Product.ProductA();
                break;
            case "B":
                product = new Product.ProductB();
                break;
        }
        return product;
    }

    /**
     * 使用反射来实现工厂类，新增产品时无需修改工厂类
     * ，但是使用反射来创建实例对象的话会比正常使用new来创建的要慢
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T extends Product> T create(Class<T> clz) {
        Product product = null;
        try {
            product = (Product) Class.forName(clz.getName()).newInstance();//反射出实例
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
