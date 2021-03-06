package com.cy.utils;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.cy.utils.common.AppUtil;
import com.cy.utils.fragment.FragmentLazyTest;
import com.cy.utils.service.RemoteControlService;
import com.cy.utils.swipeRefreshLayout.SwipeRefreshLayoutTestActivity;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Log.i(TAG, "initData: --progressName=" + AppUtil.getAppName(mContext));
    }


    public void onClick(View view) {

    }
}
