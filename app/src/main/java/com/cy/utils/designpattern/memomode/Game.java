package com.cy.utils.designpattern.memomode;

/**
 * created by cy on 2019/12/5 0005.
 * 备忘录模式
 * <p>
 * 1.定义
 * <p>
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样以后就可以将该对象恢复到先前保存的状态。
 * 2.介绍
 * <p>
 * 备忘录模式属于行为型模式。
 * 备忘录模式比较适合用于功能复杂，但是需要维护和纪录历史的地方，或者是需要保存一个或者多个属性的地方；在未来某个时刻需要时，将其还原到原来纪录的状态。
 * <p>
 * 3.UML类图
 * 备忘录模式UML类图.jpg
 * 角色说明：
 * <p>
 * Originator（发起人角色）：负责创建一个备忘录（Memoto），能够记录内部状态，以及恢复原来记录的状态。并且能够决定哪些状态是需要备忘的。
 * Memoto（备忘录角色）：将发起人（Originator）对象的内部状态存储起来；并且可以防止发起人（Originator）之外的对象访问备忘录（Memoto）。
 * Caretaker（负责人角色）：负责保存备忘录（Memoto），不能对备忘录（Memoto）的内容进行操作和访问，只能将备忘录传递给其他对象。
 * <p>
 * 4.实现
 * <p>
 * 以游戏存档为例子：
 * 4.1 创建发起人角色
 * <p>
 * 这里则是游戏类，游戏类提供存档和读档的功能：
 */
public class Game {//游戏类
    private int mLevel = 1;//等级
    private int mCoin = 0;//金币数量

    @Override
    public String toString() {
        return "game{" +
                "mLevel=" + mLevel +
                ", mCoin=" + mCoin +
                '}';
    }

    public void play() {
        System.out.println("升级了");
        mLevel++;
        System.out.println("当前等级为:" + mLevel);
        System.out.println("获得金币:32");
        mCoin += 32;
        System.out.println("当前金币数量为:" + mCoin);
    }

    public void exit() {
        System.out.println("退出游戏");
        System.out.println("退出游戏时的属性 : " + toString());
    }

    public Memento createMemento() {//创建备忘录,即游戏存档
        Memento memento = new Memento();
        memento.setLevel(mLevel);
        memento.setCoin(mCoin);
        return memento;
    }

    public void setMemento(Memento memento) {
        mLevel = memento.getLevel();
        mCoin = memento.getCoin();
        System.out.println("读取存档信息:" + toString());
    }
}
