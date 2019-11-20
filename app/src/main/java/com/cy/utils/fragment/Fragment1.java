package com.cy.utils.fragment;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.cy.utils.R;
import com.cy.utils.fragment.base.BaseLazyFragment;
import com.cy.utils.net.NetWatchdog;

/**
 * created by cy on 2019/11/20 0020.
 */
public class Fragment1 extends BaseLazyFragment {

    private NetWatchdog mNetWatchdog;

    public static Fragment getInstance() {
        Fragment fragment = new Fragment1();
        return fragment;
    }

    /**
     * 初始化网络监听
     */
    private void initNetWatchdog() {
        mNetWatchdog = new NetWatchdog(getActivity());
        mNetWatchdog.startWatch();

        mNetWatchdog.setNetConnectedListener(new NetWatchdog.NetConnectedListener() {
            @Override
            public void onReNetConnected(boolean isReconnect) {
            }

            @Override
            public void onNetUnConnected() {
            }
        });
    }

    @Override
    public void onLazyLoad() {
        Log.i(TAG, "onLazyLoad: ");
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_1;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initListener() {
        //
        initNetWatchdog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNetWatchdog.stopWatch();
    }
}
