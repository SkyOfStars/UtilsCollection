package com.cy.utils.http;

import android.os.Handler;

import androidx.annotation.NonNull;

import java.io.*;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

abstract class AbsOkHttpCallBack implements Callback {
    ReqCallBack _CallBack;
    Handler _MainThreadHandler;

    public abstract void onSuccessfulResponse(Call call, Response response);

    public AbsOkHttpCallBack(@NonNull Handler handler, @NonNull ReqCallBack callBack) {
        this._MainThreadHandler = handler;
        this._CallBack = callBack;
    }

    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            onSuccessfulResponse(call, response);
        } else {
            failedCallBack(new IOException("response code:" + response.code()));
        }
        response.close();
    }

    public void onFailure(Call call, IOException e) {
        failedCallBack(e);
    }

    public void failedCallBack(final IOException e) {
        this._MainThreadHandler.post(new Runnable() {
            public void run() {
                AbsOkHttpCallBack.this._CallBack.onFail(e);
            }
        });
    }

    public <T> void successCallBack(final T result) {
        this._MainThreadHandler.post(new Runnable() {
            public void run() {
                AbsOkHttpCallBack.this._CallBack.onSuccess(result);
            }
        });
    }
}
