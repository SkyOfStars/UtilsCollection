package com.cy.utils.view.recycleview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.cy.utils.R;
import com.cy.utils.view.leanback.FocusHighlightHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * created by cy on 2020/6/2 0002.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter implements View.OnFocusChangeListener {
    private static final String TAG = "MyRecycleViewAdapter";
    private Context mContext;
    private List<String> mDatas;
    private RecyclerView recyclerView;
    private List<Integer> heights;

    public MyRecycleViewAdapter(Context context, RecyclerView recyclerView) {
        this.mContext = context;
        this.recyclerView = recyclerView;

    }

    public void setDatas(List<String> datas) {
        if (datas == null)
            return;
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        FocusHighlightHelper.BrowseItemFocusHighlight highlight = new FocusHighlightHelper.BrowseItemFocusHighlight(1, true);
        view.setTag(R.layout.item_recycleview, highlight);
        highlight.onInitializeView(view);
        view.setFocusable(true);
        view.setOnFocusChangeListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: ");
        String s = mDatas.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTitle.setText(s);
        viewHolder.mTitle.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        FocusHighlightHelper.BrowseItemFocusHighlight highlight = (FocusHighlightHelper.BrowseItemFocusHighlight) v.getTag(R.layout.item_recycleview);
        MyLinearSmoothScroller scroller = new MyLinearSmoothScroller(mContext);

        highlight.onItemFocused(v, hasFocus);
        if (hasFocus) {
            v.bringToFront();
            int position = (int) v.findViewById(R.id.tv_title_recycleview).getTag();
//            scroller.setTargetPosition(position);
//            layoutManager.startSmoothScroll(scroller);

            int[] amount = getScrollAmount(recyclerView, v);
            recyclerView.smoothScrollBy(amount[0], amount[1],new AccelerateDecelerateInterpolator());

        } else {

        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            mTitle = view.findViewById(R.id.tv_title_recycleview);
        }
    }

    private int[] getScrollAmount(RecyclerView recyclerView, View view) {
        int[] out = new int[2];
        final int parentLeft = recyclerView.getPaddingLeft();
        final int parentTop = recyclerView.getPaddingTop();
        final int parentRight = recyclerView.getWidth() - recyclerView.getPaddingRight();
        final int childLeft = view.getLeft() + 0 - view.getScrollX();
        final int childTop = view.getTop() + 0 - view.getScrollY();
        final int dx =childLeft - parentLeft - ((parentRight - view.getWidth()) / 2);//item左边距减去Recyclerview不在屏幕内的部分，加当前Recyclerview一半的宽度就是居中

        final int dy = childTop - parentTop - (parentTop - view.getHeight()) / 2;//同上
        out[0] = dx;
        out[1] = dy;
        return out;
    }

}
