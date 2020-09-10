package com.cy.utils.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.cy.utils.BaseActivity;
import com.cy.utils.R;
import com.cy.utils.view.OvalDrawable;

/**
 * created by cy on 2020/9/10 0010.
 */
public class OvalActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.oval_layout;
    }

    @Override
    protected void initView() {
        RelativeLayout icon = findViewById(R.id.iconParent);
        OvalDrawable ovalDrawable = new OvalDrawable(icon.getResources());
        icon.setBackground(ovalDrawable);
        ovalDrawable.start();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
