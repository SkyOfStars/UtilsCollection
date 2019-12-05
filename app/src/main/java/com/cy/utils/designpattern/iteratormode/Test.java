package com.cy.utils.designpattern.iteratormode;

/**
 * created by cy on 2019/12/5 0005.
 * <p>
 * 5. 应用场景
 * <p>
 * 遍历一个容器对象时。
 * 实际我们开发中很少使用到迭代器模式。虽然不怎么用得到，但是了解其原理能够让我们在看到相关的源码（如Java中的Map、List等等容器）时能够更容易了解源码的相关思想。
 * <p>
 * 6. 优点
 * <p>
 * 可以支持以不同的方式去遍历容器对象，如顺序遍历，逆序遍历等等。
 * 符合单一职责原则，容器类与遍历算法的分离，不同类负责不同的功能。
 * <p>
 * 7. 缺点
 * <p>
 * 会产生多余的对象，消耗内存。
 * 会增多类文件。
 * 遍历过程是一个单向且不可逆的遍历。
 * 遍历过程需要注意容器是否改变，若期间改变了，可能会抛出异常。
 * <p>
 * 8. Android中的源码分析
 * <p>
 * 除了Java中的Map、List等有用到迭代器模式之外，Android中使用数据库查询时返回的Cursor游标对象，实际上就是使用了迭代器模式来实现，首先先让我们来看下怎么使用Cursor：
 */
public class Test {
    public static void main(String[] args) {
        Aggregate aggregate = new DeliveryAggregate();
        aggregate.add("1111");
        aggregate.add("2222");
        aggregate.add("3333");
        aggregate.add("9527");

        Iterator iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            String tel = (String) iterator.next();
            System.out.println("当前号码为：" + tel);
        }
        System.out.println("后面没有了");
    }
}
