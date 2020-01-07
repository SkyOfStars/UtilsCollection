package com.cy.utils.bean;

import androidx.core.util.Pools;

/**
 * created by cy on 2020/1/7 0007.
 * 经分析如果该对象池里面没有对象则会返回null,这只是一个简易的对象池
 */
public class User {

    public String id;
    public String name;
    private static final Pools.SynchronizedPool<User> sPool = new Pools.SynchronizedPool<User>(
            10);

    public static User obtain() {
        User instance = sPool.acquire();
        return (instance != null) ? instance : new User();
    }

    public void recycle() {
        sPool.release(this);
    }

}
