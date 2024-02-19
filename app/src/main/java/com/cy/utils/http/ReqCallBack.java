package com.cy.utils.http;

public interface ReqCallBack<T> {
    void onFail(Exception exc);

    void onSuccess(T t);
}
