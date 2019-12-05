package com.cy.utils.designpattern.memomode;

/**
 * created by cy on 2019/12/5 0005.
 * <p>
 * 5. 应用场景
 * <p>
 * 需要保存对象的某一时刻的状态时
 * <p>
 * 6. 优点
 * <p>
 * 能够让状态回滚到某一时刻的状态
 * 实现了状态保存对象的封装，用户无需关心其实现细节。
 * <p>
 * 7. 缺点
 * <p>
 * 要保存的对象如果成员变量过多的话，资源消耗也会相应增多。
 * <p>
 * 8. Android中的源码分析
 * <p>
 * Android中的Activity就提供了状态保存机制来保证Activity在被系统回收后能够恢复当前Activity的数据。这一机制实际上就是onSaveInstanceState和onRestoreInstanceState。
 * onSaveInstanceState就是用来保存当前Activity的状态，onRestoreInstanceState则是用来恢复Activity的状态。
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("首次进入游戏");
        Game game = new Game();
        game.play();//玩游戏
        Memento memento = game.createMemento();//创建存档
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(memento);//保存存档
        game.exit();//退出游戏

        System.out.println("-------------");
        System.out.println("二次进入游戏");
        Game secondGame = new Game();
        secondGame.setMemento(caretaker.getMemento());//读取存档
        secondGame.play();//继续玩游戏
        secondGame.exit();//不存档,嘿嘿嘿
    }
}
