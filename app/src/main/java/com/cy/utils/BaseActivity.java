package com.cy.utils;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getName();
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Log.i(TAG, "onCreate: ");
        this.mContext = BaseActivity.this;
        if (getContentView() != 0) {
            setContentView(getContentView());
        }
        initView();
        initListener();
        initData();
    }

    /**
     * @return
     */
    protected abstract int getContentView();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();
}
