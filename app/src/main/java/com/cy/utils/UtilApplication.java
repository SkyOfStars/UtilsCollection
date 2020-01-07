package com.cy.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * created by cy on 2019/11/21 0021.
 */
public class UtilApplication extends Application {
    private static final String TAG = "UtilApplication";
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        Fresco.initialize(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
