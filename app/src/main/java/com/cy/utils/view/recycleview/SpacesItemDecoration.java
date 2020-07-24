package com.cy.utils.view.recycleview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * created by cy on 2020/6/9 0009.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            //item是第一个的时候不设置间距
            outRect.left = 0;
        } else {
            outRect.left = space;
        }
    }
}
