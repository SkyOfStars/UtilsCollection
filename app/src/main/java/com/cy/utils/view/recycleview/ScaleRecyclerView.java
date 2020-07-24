package com.cy.utils.view.recycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;

/**
 * created by cy on 2020/6/3 0003.
 */
public class ScaleRecyclerView extends RecyclerView {
    public static final int FOCUS_SCROLL_ALIGNED = 0;
    private static final String TAG = "ScaleRecyclerView";
    private int mSelectedPosition = 0;

    public ScaleRecyclerView(Context context) {
        super(context);
        init();
    }

    public ScaleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScaleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //启用子视图排序功能
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    public void onDraw(Canvas c) {
        //        mSelectedPosition = getChildAdapterPosition(getFocusedChild());
        mSelectedPosition = indexOfChild(getFocusedChild());
        super.onDraw(c);
    }

    @Override
    public void bringChildToFront(View child) {
        super.bringChildToFront(child);
        Log.i(TAG, "bringChildToFront");
        mSelectedPosition = indexOfChild(child);
    }

    public final boolean isChildrenDrawingOrderEnabledInternal() {
        return isChildrenDrawingOrderEnabled();
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {

        int position = mSelectedPosition;
        Log.i(TAG, "getChildDrawingOrder: mSelectedPosition=" + position + "--i=" + i + "--childCount=" + childCount);
        if (position < 0) {
            return i;
        } else {
            if (i == childCount - 1) {
                if (position > i) {
                    position = i;
                }
                return position;
            }
            if (i == position) {
                return childCount - 1;
            }
        }
        return i;
    }


}
