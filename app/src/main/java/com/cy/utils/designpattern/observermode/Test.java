package com.cy.utils.designpattern.observermode;

/**
 * created by cy on 2019/11/25 0025.
 * <p>
 * <p>
 * 6. 优点
 * <p>
 * 解除观察者与主题之间的耦合。让耦合的双方都依赖于抽象，而不是依赖具体。从而使得各自的变化都不会影响另一边的变化。
 * 易于扩展，对同一主题新增观察者时无需修改原有代码。
 * <p>
 * 7. 缺点
 * <p>
 * 依赖关系并未完全解除，抽象主题仍然依赖抽象观察者。
 * 使用观察者模式时需要考虑一下开发效率和运行效率的问题，程序中包括一个被观察者、多个观察者，开发、调试等内容会比较复杂，
 * 而且在Java中消息的通知一般是顺序执行，那么一个观察者卡顿，会影响整体的执行效率，在这种情况下，一般会采用异步实现。
 * 可能会引起多余的数据通知
 * <p>
 * Android中应用的场景：
 * 1、 控件中Listener监听方式；
 * 2、Adapter的notifyDataSetChanged()方法；
 * 3 、BroadcastReceiver；
 * 4、另外,一些著名的第三方事件总线库,比如RxJava、RxAndroid、EventBus、otto等等,也是使用了观察者模式。
 */
public class Test {

    public static void main(String[] args) {//观察者客户端调用
        Observable postman = new Postman();

        Observer boy1 = new Boy("路飞");
        Observer boy2 = new Boy("乔巴");
        Observer girl1 = new Girl("娜美");

        postman.add(boy1);
        postman.add(boy2);
        postman.add(girl1);
        postman.notify("快递到了,请下楼领取.");
    }
}
