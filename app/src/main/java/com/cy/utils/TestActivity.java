package com.cy.utils;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试Fresco框架
 * created by cy on 2019/12/20 0020.
 */
public class TestActivity extends BaseActivity {


    private SimpleDraweeView draweeView;
    private ListView listView;

    @Override
    protected int getContentView() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        draweeView = (SimpleDraweeView) findViewById(R.id.draweeview);
        listView = findViewById(R.id.listview);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://空闲状态
                        Fresco.getImagePipeline().resume();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING://滚动状态
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://触摸后滚动
                        Fresco.getImagePipeline().pause();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        Uri uri = Uri.parse("https://images0.cnblogs.com/blog2015/51591/201506/151449315917675.jpg");
        Uri uri2 = Uri.parse("http://img.huofar.com/data/jiankangrenwu/shizi.gif");
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri2)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();

        draweeView.setController(draweeController);
        //        draweeView.setImageURI(uri2);
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        listView.setAdapter(myBaseAdapter);
        myBaseAdapter.setData(getData());
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("https://img-blog.csdnimg.cn/20190323161159133.png");
        data.add("https://img-blog.csdnimg.cn/20190323161221635.png");
        data.add("https://img-blog.csdnimg.cn/20190323161242873.png");
        data.add("https://img-blog.csdnimg.cn/20190323161318683.png");
        data.add("https://img-blog.csdnimg.cn/20190323161332979.png");
        data.add("https://img-blog.csdnimg.cn/20190323161354536.png");
        data.add("https://img-blog.csdnimg.cn/20190323161418695.png");
        data.add("https://img-blog.csdnimg.cn/20190323161438219.png");
        data.add("https://img-blog.csdnimg.cn/20190323161457466.png");
        data.add("https://img-blog.csdnimg.cn/20190323161535761.png");
        data.add("https://images0.cnblogs.com/blog2015/51591/201506/151449465134350.jpg");
        data.add("https://images0.cnblogs.com/blog2015/51591/201506/251236333619887.png");
        data.add("http://szimg.focus.cn/upload/photos/50516//aBUMfzzb.jpg");
        return data;
    }

    private class MyBaseAdapter extends BaseAdapter {
        private List<String> list = new ArrayList<>();

        public void setData(List<String> data) {
            if (data == null)
                return;
            list = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.listview_item_test_fre, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String url = list.get(position);
            viewHolder.simpleDraweeView.setImageURI(url);
            return convertView;
        }
    }

    static class ViewHolder {
        public SimpleDraweeView simpleDraweeView;

        public ViewHolder(View view) {
            simpleDraweeView = view.findViewById(R.id.item_draweeview);
        }
    }
}
