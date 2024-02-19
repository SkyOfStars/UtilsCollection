package com.cy.utils.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CaoYanYan
 * Date: 2024/2/2 11:34
 **/
public class OkHttpUtil {
    private static final long DEFAULT_TIMEOUT = -1;
    private static Handler _MainThreadHandler = new Handler(Looper.getMainLooper());

    private static Call buildCall(@NonNull String url, @Nullable Map<String, String> herders, @Nullable RequestBody body, long connectTimeout, long readTimeout) throws IOException {
        try {
            Log.d("http", "url=" + url);
            OkHttpClient okClient = buildOkHttpClient(connectTimeout, readTimeout);
            Builder builder = new Builder().url(url);
            if (herders != null) {
                builder.headers(Headers.of(herders));
            }
            if (body != null) {
                builder.post(body);
            }
            return okClient.newCall(builder.build());
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    private static OkHttpClient buildOkHttpClient(long connectTimeout, long readTimeout) {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder().readTimeout(5000, TimeUnit.MILLISECONDS);
        if (connectTimeout > 0) {
            okClientBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        }
        if (readTimeout > 0) {
            okClientBuilder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
        }
        return okClientBuilder.build();
    }

    public static void doGetAsync(@NonNull String url, @NonNull Callback callback, long connectTimeout, long readTimeout) {
        doGetAsync(url, null, connectTimeout, readTimeout, callback);
    }

    public static void doGetAsync(@NonNull String url, @Nullable Map<String, String> herders, long connectTimeout, long readTimeout, @NonNull Callback callback) {
        try {
            buildCall(url, herders, null, connectTimeout, readTimeout).enqueue(callback);
        } catch (IOException e) {
            callback.onFailure(null, e);
        }
    }

    public static Response doGetSync(@NonNull String url) throws IOException {
        return doGetSync(url, null, -1, -1);
    }

    public static Response doGetSync(@NonNull String url, @Nullable Map<String, String> herders, long connectTimeout, long readTimeout) throws IOException {
        return buildCall(url, herders, null, connectTimeout, readTimeout).execute();
    }

    public static void doPostAsync(@NonNull String url, @Nullable Map<String, String> herders, @NonNull RequestBody body, long connectTimeout, long readTimeout, @NonNull Callback callback) {
        try {
            buildCall(url, herders, body, connectTimeout, readTimeout).enqueue(callback);
        } catch (IOException e) {
            callback.onFailure(null, e);
        }
    }

    public static void downloadFile(@NonNull String url, @Nullable Map<String, String> herders, @NonNull File file, long connectTimeout, long readTimeout, @NonNull ReqProgressCallBack<File> callBack) {
        doGetAsync(url, herders, connectTimeout, readTimeout, new DownLoadFileCallBack(_MainThreadHandler, file, callBack));
    }

    public static void downLoadFile(String url, @Nullable Map<String, String> herders, String savePath, String fileName, long connectTimeout, long readTimeout, ReqProgressCallBack<File> callBack) {
        downloadFile(url, herders, new File(savePath, fileName), connectTimeout, readTimeout, callBack);
    }

    public static void downloadFile(@NonNull String url, @NonNull String savePath, @NonNull String fileName, @NonNull ReqProgressCallBack<File> callBack) {
        downLoadFile(url, null, savePath, fileName, -1, -1, callBack);
    }

    public static void downloadFile(@NonNull String url, @NonNull File file, @NonNull ReqProgressCallBack<File> callBack) {
        downloadFile(url, null, file, -1, -1, callBack);
    }

    public static void getString(@NonNull String url, boolean useAes, @NonNull ReqCallBack<String> callBack) {
        getString(url, null, useAes, -1, -1, callBack);
    }

    public static void getString(@NonNull String url, boolean useAes, long connectTimeout, long readTimeout, @NonNull ReqCallBack<String> callBack) {
        getString(url, null, useAes, connectTimeout, readTimeout, callBack);
    }

    public static void getString(@NonNull String url, @Nullable Map<String, String> headers, boolean useAes, long connectTimeout, long readTimeout, @NonNull ReqCallBack<String> callBack) {
        doGetAsync(url, headers, connectTimeout, readTimeout, new StringCallBack(useAes, _MainThreadHandler, callBack));
    }

    public static void postString(@NonNull String url, @Nullable Map<String, String> headers, @NonNull String content, @NonNull ReqCallBack<String> callBack) {
        doPostAsync(url, headers, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content), -1, -1, new StringCallBack(false, _MainThreadHandler, callBack));
    }

    public static void postTextString(@NonNull String url, @Nullable Map<String, String> headers, @NonNull String content, @NonNull ReqCallBack<String> callBack) {
        doPostAsync(url, headers, RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), content), -1, -1, new StringCallBack(false, _MainThreadHandler, callBack));
    }

    public static void postString(@NonNull String url, @NonNull String content, @NonNull ReqCallBack<String> callBack) {
        postString(url, null, content, callBack);
    }

    public static void postForm(@NonNull String url, @Nullable Map<String, String> headers, @NonNull Map<String, String> params, @NonNull ReqCallBack<String> callBack) {
        Set<String> keys = params.keySet();
        StringBuilder content = new StringBuilder();
        for (String key : keys) {
            content.append(key);
            content.append("=");
            content.append((String) params.get(key));
            content.append("&");
        }
        doPostAsync(url, headers, RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), content.toString()), -1, -1, new StringCallBack(false, _MainThreadHandler, callBack));
    }

    public static void postForm(@NonNull String url, @NonNull Map<String, String> params, @NonNull ReqCallBack<String> callBack) {
        postForm(url, null, params, callBack);
    }

    @Nullable
    public static String getString(@NonNull String url, boolean useAes) throws IOException {
        return getString(url, null, useAes, -1, -1);
    }

    @Nullable
    public static String getString(@NonNull String url, @Nullable Map<String, String> headers, boolean useAes, long connectTimeout, long readTimeout) throws IOException {
        Response response = doGetSync(url, headers, connectTimeout, readTimeout);
        if (!response.isSuccessful()) {
            throw new IOException("response code:" + response.code());
        } else if (response.body() == null) {
            return null;
        } else {
            return response.body().string();
        }
    }
}
