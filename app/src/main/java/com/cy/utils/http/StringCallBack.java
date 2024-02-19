package com.cy.utils.http;

import android.os.Handler;

import androidx.annotation.NonNull;

import java.io.*;

import okhttp3.Call;
import okhttp3.Response;

class StringCallBack extends AbsOkHttpCallBack {
    private boolean useAes;

    public StringCallBack(boolean useAes2, @NonNull Handler handler, @NonNull ReqCallBack<String> callBack) {
        super(handler, callBack);
        this.useAes = useAes2;
    }

    public void onSuccessfulResponse(Call call, Response response) {
        try {
            if (response.body() == null) {
                throw new IOException("the body is null!");
            }
            String result = response.body().string();
            if (this.useAes) {
                result = QCast.getString(result);
            }
            successCallBack(result);
        } catch (IOException e) {
            e.printStackTrace();
            failedCallBack(e);
        }
    }
}