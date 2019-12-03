package com.cy.utils.designpattern.statemode;

/**
 * created by cy on 2019/12/3 0003.
 * <p>创建环境类
 * 定义客户端需要的接口，以及负责状态的切换：
 */
public class Context {
    private PersonState mPersonState;

    public void setPersonState(PersonState personState) {
        mPersonState = personState;
    }

    public void fallInLove() {
        System.out.println("恋爱了,陷入热恋状态:");
        setPersonState(new ConcretePersonState.LoveState());
    }

    public void disappointmentInLove() {
        System.out.println("失恋了,变成单身狗状态:");
        setPersonState(new ConcretePersonState.DogState());
    }

    public void movies() {
        mPersonState.movies();
    }

    public void shopping() {
        mPersonState.shopping();
    }
}
