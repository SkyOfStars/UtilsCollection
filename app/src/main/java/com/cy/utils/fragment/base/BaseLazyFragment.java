package com.cy.utils.fragment.base;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

/**
 * 懒加载
 * 测试随着 Fragment切换View会销毁重建走onCreateView、onViewCreated，onActivityCreated，onResume，
 * 但是onLazyLoad()只加载一次，使用的话建议数据处理都在但是onLazyLoad中处理；
 *
 * a、该方案适合手动请求网络进行刷新的需求
 * b、如果有每次切换都涉及网络数据请求刷新的需求，则需要重新修改调整；
 *
 */
public abstract class BaseLazyFragment extends Fragment {
    protected   final String TAG = this.getClass().getName();
    /**
     * 懒加载过
     */
    public boolean isLazyLoaded;
    /**
     * Fragment的View加载完毕的标记
     */
    private boolean isPrepared;

    /**
     * 第一步,改变isPrepared标记
     * 当onViewCreated()方法执行时,表明View已经加载完毕,此时改变isPrepared标记为true,并调用lazyLoad()方法
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
        isPrepared = true;
        //只有Fragment onCreateView好了
        //另外这里调用一次lazyLoad()
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(getContentView(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
        initView(view);
        initListener();
    }

    /**
     * 第二步
     * 此方法会在onCreateView()之前执行
     * 当viewPager中fragment改变可见状态时也会调用
     * 当fragment 从可见到不见，或者从不可见切换到可见，都会调用此方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    /**
     * 调用懒加载
     * 第三步:在lazyLoad()方法中进行双重标记判断,通过后即可进行数据加载
     */
    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = true;
        }
    }

    /**
     * 第四步:定义抽象方法onLazyLoad(),具体加载数据的工作,交给子类去完成
     */
    @UiThread
    public abstract void onLazyLoad();


    /**
     * 返回一个用于显示界面的布局id
     */
    public abstract int getContentView();

    /**
     * 初始化View的代码写在这个方法中
     */
    public abstract void initView(View view);

    /**
     * 初始化监听器的代码写在这个方法中
     */
    public abstract void initListener();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
