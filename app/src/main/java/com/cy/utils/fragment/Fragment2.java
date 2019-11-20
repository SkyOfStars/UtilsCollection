package com.cy.utils.fragment;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.cy.utils.R;
import com.cy.utils.fragment.base.BaseLazyFragment;

/**
 * created by cy on 2019/11/20 0020.
 */
public class Fragment2 extends BaseLazyFragment {

    public static Fragment getInstance() {
        Fragment fragment = new Fragment2();
        return fragment;
    }

    @Override
    public void onLazyLoad() {
        Log.i(TAG, "onLazyLoad: ");
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_2;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initListener() {

    }
}
