package com.cy.utils.designpattern.statemode;

/**
 * created by cy on 2019/12/3 0003.
 * 状态模式测试
 * <p>
 * a. 应用场景
 * <p>
 * 对象的行为取决于其状态，随着状态改变时其行为也需改变
 * 包含大量的与状态相关的条件判断语句时
 * <p>
 * b. 优点
 * <p>
 * 每个状态都是一个子类，易于扩展和维护。
 * 避免过多的条件语句，使得结构更清晰，提高代码的可维护性。
 * <p>
 * c. 缺点
 * <p>
 * 可能会导致状态子类会过多
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        context.fallInLove();
        context.shopping();
        context.movies();
        context.disappointmentInLove();
        context.shopping();
        context.movies();
    }
}
