package com.cy.utils.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.cy.utils.view.MonitorView;

/**
 * created by cy on 2020/1/16 0016.
 */
public final class MonitorUtil {

    /**
     * 性能测试开关状态.
     */
    public static boolean mMonitorOpened = false;

    /**
     * 帧测试View.
     */
    private static MonitorView mAbMonitorView = null;

    /**
     * 帧测试Handler.
     */
    private static Handler mMonitorHandler = new Handler();

    /**
     * 帧测试线程.
     */
    private static Runnable mMonitorRunnable = null;

    /**
     * Window 管理器.
     */
    private static WindowManager mWindowManager = null;

    /**
     * 帧测试参数.
     */
    private static WindowManager.LayoutParams mMonitorParams = null;


    /**
     * 描述：打开关闭帧数测试
     * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
     * lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG
     */
    public static void openMonitor(Context context) {

        if (mMonitorOpened) {
            return;
        }
        mWindowManager = ((Activity) context).getWindowManager();

        DisplayMetrics display = AppUtil.getDisplayMetrics(context);
        final int diaplayWidth = display.widthPixels;

        mAbMonitorView = new MonitorView(context);
        mMonitorParams = new WindowManager.LayoutParams();
        // 设置window type
        mMonitorParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        /*
         * 如果设置为params.type = WindowManager.LayoutParams.TYPE_PHONE;
         * 那么优先级会降低一些, 即拉下通知栏不可见
         */
        //设置图片格式，效果为背景透明
        mMonitorParams.format = PixelFormat.RGBA_8888;
        // 设置Window flag
        mMonitorParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        /*
         * 下面的flags属性的效果形同“锁定”。
         * 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
        wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
                               | LayoutParams.FLAG_NOT_FOCUSABLE
                               | LayoutParams.FLAG_NOT_TOUCHABLE;
         */
        // 设置悬浮窗的长得宽
        mMonitorParams.width = ViewUtil.scale(context, 100);
        mMonitorParams.height = ViewUtil.scale(context, 50);
        mWindowManager.addView(mAbMonitorView, mMonitorParams);
        mMonitorOpened = true;
        mMonitorRunnable = new Runnable() {

            @Override
            public void run() {
                mAbMonitorView.postInvalidate();
                mMonitorHandler.postDelayed(this, 0);
            }
        };
        mMonitorHandler.postDelayed(mMonitorRunnable, 0);

        mAbMonitorView.setOnTouchListener(new View.OnTouchListener() {
            int lastX, lastY;
            int paramX, paramY;

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        paramX = mMonitorParams.x;
                        paramY = mMonitorParams.y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        if ((paramX + dx) > diaplayWidth / 2) {
                            mMonitorParams.x = diaplayWidth;
                        } else {
                            mMonitorParams.x = 0;
                        }
                        mMonitorParams.x = paramX + dx;
                        mMonitorParams.y = paramY + dy;
                        // 更新悬浮窗位置
                        mWindowManager.updateViewLayout(mAbMonitorView, mMonitorParams);
                        break;
                }
                return true;
            }
        });

    }

    /**
     * 描述：关闭帧数测试.
     */
    public static void closeMonitor() {
        if (mMonitorOpened) {
            if (mWindowManager != null && mAbMonitorView != null) {
                mWindowManager.removeView(mAbMonitorView);
            }
            mMonitorOpened = false;
            if (mMonitorHandler != null && mMonitorRunnable != null) {
                mMonitorHandler.removeCallbacks(mMonitorRunnable);
            }
        }

    }

}
