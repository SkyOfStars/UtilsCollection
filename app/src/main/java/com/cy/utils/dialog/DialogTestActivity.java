package com.cy.utils.dialog;

import com.cy.utils.BaseActivity;
import com.cy.utils.R;

/**
 * created by cy on 2020/7/31 0031.
 */
public class DialogTestActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void initView() {
        new MyDialog(mContext, R.style.MyDialog).setClickCallback(new MyDialog.IClickCallback() {
            @Override
            public void confirm() {

            }

            @Override
            public void cancel() {

            }
        }).show();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
