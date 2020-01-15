package com.cy.utils;

import android.content.Intent;
import android.view.View;

import com.cy.utils.fragment.FragmentLazyTest;
import com.cy.utils.swipeRefreshLayout.SwipeRefreshLayoutTestActivity;

public class MainActivity extends BaseActivity {


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
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fragment_lazyload_text:
                startActivity(new Intent(mContext, FragmentLazyTest.class));
                break;
            case R.id.btn_wipeRefreshLayoutTest:
                startActivity(new Intent(mContext, SwipeRefreshLayoutTestActivity.class));
                break;
            default:
                break;
        }
    }
}
