package com.cy.utils.designpattern.responsibilitychainmode;

/**
 * created by cy on 2019/12/3 0003.
 * <p>
 * a .说明：
 * <p>
 * 上面的请求只是一个简单的地址字符串，如果是一些复杂的请求，可以封装成独立的对象。如：普通快递和生鲜快递，生鲜快递还需快递员做冷链处理等等。
 * 请求实际上可以从责任链中的任意结点开始，即可以从上海快递员开始处理也行；
 * 责任链中的结点顺序实际也可以调整，即北京->广州->上海的顺序也行；
 * 责任链也可以越过某些结点去处理请求，如北京->广州，越过了上海。
 * 对于请求，只有两种结果：一是某个结点对其进行了处理，如上面例子的上海、广州快递，这种叫纯的责任链；另一个则是所有结点都不进行处理，如美国的快递，这种叫不纯的责任链。我们所见到的基本都是不纯的责任链。
 * <p>
 * b. 应用场景
 * <p>
 * 多个对象处理同一请求时，但是具体由哪个对象去处理需要运行时做判断。
 * 具体处理者不明确的情况下，向这组对象提交了一个请求。
 * <p>
 * c. 优点
 * <p>
 * 代码的解耦，请求者与处理者的隔离分开。
 * 易于扩展，新增处理者往链上加结点即可。
 * <p>
 * d. 缺点
 * <p>
 * 责任链过长的话，或者链上的结点判断处理时间太长的话会影响性能，特别是递归循环的时候。
 * 请求有可能遍历完链都得不到处理。
 * <p>
 * e. Android中的源码分析
 * <p>
 * Android中的事件分发机制就是类似于责任链模式，关于事件分发机制，这里先不详述了，先占个坑，后面另起文章说明。
 * 另外，OKhttp中对请求的处理也是用到了责任链模式，有兴趣的可以去看下OKhttp的源码。后面有时间也会对OKhttp的源码进行分析。
 */
public class Test {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        //创建不同的快递员对象
        Postman beijingPostman = new Handler.BeijingPostman();
        Postman shanghaiPostman = new Handler.ShanghaiPostman();
        Postman guangzhouPostman = new Handler.GuangzhouPostman();

        //创建下一个结点
        beijingPostman.nextPostman = shanghaiPostman;
        shanghaiPostman.nextPostman = guangzhouPostman;

        //处理不同地区的快递，都是从首结点北京快递员开始
        System.out.println("有一个上海快递需要派送:");
        beijingPostman.handleCourier("Shanghai");
        System.out.println("有一个广州快递需要派送:");
        beijingPostman.handleCourier("Guangzhou");
        System.out.println("有一个美国快递需要派送:");
        beijingPostman.handleCourier("America");

    }
}
