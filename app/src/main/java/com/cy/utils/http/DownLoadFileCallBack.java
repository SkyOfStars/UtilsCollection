package com.cy.utils.http;

import android.os.Handler;

import androidx.annotation.NonNull;

import java.io.*;

public class DownLoadFileCallBack extends AbsOkHttpCallBack {
    private static final String TAG = "DownLoadFileCallBack";
    private File _File;

    public DownLoadFileCallBack(@NonNull Handler handler, File file, @NonNull ReqProgressCallBack<File> callBack) {
        super(handler, callBack);
        this._File = file;
    }

    public void onSuccessfulResponse(okhttp3.Call r14, okhttp3.Response r15) {

    }

    public void progressCallBack(long total, long current) {
        final long j = total;
        final long j2 = current;
        this._MainThreadHandler.post(new Runnable() {
            public void run() {
                ((ReqProgressCallBack) DownLoadFileCallBack.this._CallBack).onProgress(j, j2);
            }
        });
    }
}

