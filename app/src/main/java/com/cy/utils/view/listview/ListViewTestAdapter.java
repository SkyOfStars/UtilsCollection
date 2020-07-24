package com.cy.utils.view.listview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cy.utils.R;
import com.cy.utils.view.leanback.FocusHighlightHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * created by cy on 2019/12/3 0003.
 * 用于ListView进行测试
 */
public class ListViewTestAdapter extends BaseAdapter {
    private static final String TAG = "ListViewTestAdapter";
    private List<String> data = new ArrayList<>();
    private Context mContext;
    ListViewTestAdapter( Context context){
    this.mContext=context;
    }
    public void setData(List<String> data){
        if (data==null){
            return;
        }
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.i(TAG, "getView: ");
        ViewHolder viewHolder;
        final FocusHighlightHelper. BrowseItemFocusHighlight s;
        if (view==null){
            view= View.inflate(mContext,R.layout.item_listview_test_leanback,null);
            viewHolder = new ViewHolder(view);
            view.setTag(R.layout.item_listview_test_leanback,viewHolder);
            s= new FocusHighlightHelper.BrowseItemFocusHighlight(3,true);
            view.setTag(R.id.tv_title,s);



        }else {
            s = (FocusHighlightHelper.BrowseItemFocusHighlight) view.getTag(R.id.tv_title);
            viewHolder= (ViewHolder) view.getTag(R.layout.item_listview_test_leanback);
        }
        viewHolder.textView.setText(data.get(i));
        viewHolder.textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG,"onFocusChange--hasFocus="+hasFocus);
                s.onItemFocused(v,hasFocus);
            }
        });
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            s.onItemFocused(v,true);
            }
        });
        return view;
    }

    static class ViewHolder {
        public Button textView;
        ViewHolder(View view){
            textView = view.findViewById(R.id.tv_title);
        }
    }

}
