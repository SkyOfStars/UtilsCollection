package com.cy.utils.view.recycleview;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 给RecyclerView添加点击事件监听：目前在可以触屏的设备上有效，电视上无效；
 * created by cyy on 2019/9/12 0012.
 */
public abstract class RecyclerViewItemTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat mGestureDetectorCompat;
    private RecyclerView mRecyclerView;

    public RecyclerViewItemTouchListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(), new MyGestureListener());
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(child);
                onItemClick(viewHolder);
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View childe = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (childe != null) {
                RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(childe);
                onItemLongClick(viewHolder);
            }
        }
    }

    /**
     * 点击事件
     *
     * @param viewHolder
     */
    public abstract void onItemClick(RecyclerView.ViewHolder viewHolder);

    /**
     * 长按时间
     *
     * @param viewHolder
     */
    public abstract void onItemLongClick(RecyclerView.ViewHolder viewHolder);
}