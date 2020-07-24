package com.cy.utils.view.recycleview;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cy.utils.BaseActivity;
import com.cy.utils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * created by cy on 2020/6/2 0002.
 */
public class RecycleViewActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        ScaleRecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(16));
        //设置增加或删除条目的动画

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MyRecycleViewAdapter myRecycleViewAdapter = new MyRecycleViewAdapter(RecycleViewActivity.this, recyclerView);
        recyclerView.setAdapter(myRecycleViewAdapter);
        myRecycleViewAdapter.setDatas(getDatas());

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
    }

    private List<String> getDatas() {
        Log.i(TAG, "getDatas: ");
        List<String> list = new ArrayList<>();
        list.add("text1");
        list.add("text2");
        list.add("text3");
        list.add("text4");
        list.add("text5");
        list.add("text6");
        list.add("text7");
        list.add("text8");
        list.add("text9");
        list.add("text10");
        list.add("text11");
        return list;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        View view = getCurrentFocus();
        Log.i("xxx", "onKeyDown view==" + view);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        Log.i("xxx", "dispatchTouchEvent view==" + view);
        return super.dispatchTouchEvent(ev);
    }

}
