package com.cy.utils.view.listview;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cy.utils.BaseActivity;
import com.cy.utils.R;
import com.cy.utils.view.leanback.FocusHighlightHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * created by cy on 2019/12/3 0003.
 */
public class ListViewTestActivity extends BaseActivity {
    private static final String TAG = "ListViewTestActivity";
    private ListView mListView;

    @Override
    protected int getContentView() {
        return R.layout.activity_listview_test;
    }

    @Override
    protected void initView() {
        mListView = findViewById(R.id.listview_test);

        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                FocusHighlightHelper.BrowseItemFocusHighlight s = (FocusHighlightHelper.BrowseItemFocusHighlight) view.getTag(R.id.tv_title);
//                s.onItemFocused(view, false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Log.i(TAG, "initData: ");
        ListViewTestAdapter testAdapter = new ListViewTestAdapter(mContext);

        getDatas();
        mListView.setAdapter(testAdapter);
        testAdapter.setData(getDatas());
    }

    private List<String> getDatas() {
        Log.i(TAG, "getDatas: ");
        List<String> list = new ArrayList<>();
        list.add("text1");
        list.add("text2");
        list.add("text3");
        list.add("text4");
        return list;
    }
}
