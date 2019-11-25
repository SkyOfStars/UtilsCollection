package com.cy.utils.designpattern.singletonmode;

import java.util.HashMap;
import java.util.Map;

/**
 * created by cy on 2019/11/25 0025.
 * <p>
 * 使用容器实现单例模式
 * <p>
 * <p>
 * 优点：方便管理。
 * 缺点：写法稍复杂。
 */
public class SingletonManager {

    private static Map<String, Object> objMap = new HashMap<String, Object>();

    public static void registerService(String key, Object instance) {
        if (!objMap.containsKey(key)) {
            objMap.put(key, instance);//添加单例
        }
    }

    public static Object getService(String key) {
        return objMap.get(key);//获取单例
    }
}
