package com.cy.utils.http;

public interface ReqProgressCallBack<T> extends ReqCallBack<T> {
    void onProgress(long j, long j2);
}