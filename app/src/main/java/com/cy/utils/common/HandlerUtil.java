package com.cy.utils.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Handler工具类
 * Created by CaoYanYan
 * Date: 2024/3/5 14:39
 **/
public class HandlerUtil {
    private HandlerUtil() {
    }

    /**
     * 用于主线程处理
     */
    private static Handler sMainHandler = null;
    private static Handler sBackgroundHandler = null;

    private static Handler getsMainHandler() {
        if (sMainHandler == null) {
            sMainHandler = new Handler(Looper.getMainLooper());
        }
        return sMainHandler;
    }

    public static void exeByMainThread(Runnable runnable) {
        exeByMainThread(runnable, 0);
    }

    public static void exeByMainThread(Runnable runnable, int delayTime) {
        getsMainHandler().postDelayed(runnable, delayTime);
    }

    public static void exeByBackgroundThread(Runnable runnable) {
        exeByBackgroundThread(runnable, 0);
    }

    public static void exeByBackgroundThread(Runnable runnable, int delayTime) {
        getBackgroundHandler().postDelayed(runnable, delayTime);
    }

    /**
     * 添加任务线性执行
     * @return
     */
    private static Handler getBackgroundHandler() {
        if (sBackgroundHandler == null) {
            HandlerThread handlerThread = new HandlerThread("background");
            handlerThread.start();
            sBackgroundHandler = new Handler(handlerThread.getLooper());
        }
        return sBackgroundHandler;
    }
}
