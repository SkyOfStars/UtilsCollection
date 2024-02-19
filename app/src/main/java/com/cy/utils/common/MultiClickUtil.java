package com.cy.utils.common;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cy.utils.application.UtilApplication;


/**
 * 用于防止短时间内频繁点击按钮
 * Created by CaoYanYan
 * Date: 2023/8/23 11:31
 **/
public class MultiClickUtil {
    private static final String TAG = "MultiClickUtil";

    public static void setOnMultiClick(View view, OnMultiClickListener onMultiClickListener) {
        setOnMultiClick(view, onMultiClickListener, 1 * 1000);
    }

    public static void setOnMultiClick(View view, OnMultiClickListener onMultiClickListener, int time) {
        if (view == null || onMultiClickListener == null) {
            Log.e(TAG, "the value is null ");
            return;
        }
        if (time < 0) {
            Log.e(TAG, "the time is error ");
            return;
        }
        onMultiClickListener.setMinTime(time);
        view.setOnClickListener(onMultiClickListener);
    }

    public static abstract class OnMultiClickListener implements View.OnClickListener {
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        private int minIntervalTime;
        private long lastClickTime;

        @Override
        public void onClick(View v) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= minIntervalTime) {
                // 超过点击间隔后再将lastClickTime重置为当前点击时间
                lastClickTime = curClickTime;
                onMultiClick(v);
            } else {
                Toast.makeText(UtilApplication.getContext(), "不能多次点击", Toast.LENGTH_SHORT).show();
            }
        }

        public void setMinTime(int minTime) {
            this.minIntervalTime = minTime;
        }

        public abstract void onMultiClick(View vew);
    }
}
