package com.cy.utils.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.cy.utils.common.AppUtil;

public class RemoteControlService extends Service {
    private static final String TAG = "RemoteControlService";

    public RemoteControlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "initData: --progressName=" + AppUtil.getAppName(this));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        startForeground(1, new Notification());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        stopForeground(true);
    }
}
