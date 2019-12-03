package com.cy.utils.view.listview;

import android.widget.ListView;

import com.cy.utils.BaseActivity;
import com.cy.utils.R;

/**
 * created by cy on 2019/12/3 0003.
 */
public class ListViewTestActivity extends BaseActivity {

    private ListView mListView;

    @Override
    protected int getContentView() {
        return R.layout.activity_listview_test;
    }

    @Override
    protected void initView() {
        mListView = findViewById(R.id.listview_test);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mListView.setAdapter(new ListViewTestAdapter());
    }
}
