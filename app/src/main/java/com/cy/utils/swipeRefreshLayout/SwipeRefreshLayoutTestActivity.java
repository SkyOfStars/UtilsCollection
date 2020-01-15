package com.cy.utils.swipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cy.utils.BaseActivity;
import com.cy.utils.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by cy on 2020/1/15 0015.
 */
public class SwipeRefreshLayoutTestActivity extends BaseActivity {

    private ArrayAdapter mAdapter;
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private static final int REFRESH_UI = 0;
    private List<String> mDatas = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_UI:
                    mDatas.addAll(Arrays.asList("赵云", "关于", "郭嘉"));
                    //刷新adapter
                    mAdapter.notifyDataSetChanged();
                    //为了保险起见可以先判断当前是否在刷新中（旋转的小圈圈在旋转）....
                    if (mSwipeLayout.isRefreshing()) {
                        //关闭刷新动画
                        mSwipeLayout.setRefreshing(false);
                    }
                    break;

            }
        }

        ;
    };


    @Override
    protected int getContentView() {
        return R.layout.activity_swiperefreshlayout_test;
    }

    @Override
    protected void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
    }

    @Override
    protected void initListener() {
        //设置在listview上下拉刷新的监听
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里可以做一下下拉刷新的操作
                //例如下面代码，在方法中发送一个handler模拟延时操作
                mHandler.sendEmptyMessageDelayed(REFRESH_UI, 2000);
            }
        });
    }

    @Override
    protected void initData() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);
    }
}
