package com.cy.utils.designpattern.memomode;

/**
 * created by cy on 2019/12/5 0005.
 * <p>
 * 2、创建备忘录角色
 */
public class Memento {//备忘录类
    public int level;//等级
    public int coin;//金币数量

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getLevel() {
        return level;
    }

    public int getCoin() {
        return coin;
    }
}
