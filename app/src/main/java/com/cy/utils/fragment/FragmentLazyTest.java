package com.cy.utils.fragment;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.cy.utils.BaseActivity;
import com.cy.utils.R;
import com.cy.utils.view.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * created by cy on 2019/11/20 0020.
 * 用fragment懒加载测试
 * <p>
 * 随着 Fragment切换View会销毁重建走onCreateView、onViewCreated，onActivityCreated，onResume，
 * 但是onLazyLoad()只加载一次，使用的话建议数据处理都在但是onLazyLoad中处理；
 * <p>
 * a、该方案适合手动请求网络进行刷新的需求
 * b、如果有每次切换都涉及网络数据请求刷新的需求，则需要重新修改调整；
 */
public class FragmentLazyTest extends BaseActivity {
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    /**
     * fragment,包含单集，系列
     */
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    /**
     * fragment的名称
     */
    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    public int getContentView() {
        return R.layout.activity_fragment_lazyload_test;
    }

    @Override
    public void initView() {
        mPagerSlidingTabStrip = findViewById(R.id.alivc_home_pager_tab_trip);
        mViewPager = findViewById(R.id.alivc_home_viewpager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTitles.add(getResources().getString(R.string.fragment1));
        mTitles.add(getResources().getString(R.string.fragment2));
        mTitles.add(getResources().getString(R.string.fragment3));
        mTitles.add(getResources().getString(R.string.fragment4));
        mTitles.add(getResources().getString(R.string.fragment5));
        mTitles.add(getResources().getString(R.string.fragment6));

        mFragments.add(Fragment1.getInstance());
        mFragments.add(Fragment2.getInstance());
        mFragments.add(Fragment3.getInstance());
        mFragments.add(Fragment4.getInstance());
        mFragments.add(Fragment5.getInstance());
        mFragments.add(Fragment6.getInstance());

        AlivcFragmentPagerAdapter pagerAdapter = new AlivcFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(pagerAdapter);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(1);
        setPagerSlidingTabStripValue();
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setPagerSlidingTabStripValue() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        // 设置Tab是自动填充满屏幕的
        mPagerSlidingTabStrip.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1f, dm));
        // 设置Tab Indicator的高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4f, dm));
        // 设置Tab标题文字的大小
        mPagerSlidingTabStrip.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16f, dm));
        // 设置Tab Indicator的颜色
        mPagerSlidingTabStrip.setIndicatorColor(ContextCompat.getColor(getApplicationContext(), R.color.slide_tab_orange));
        // 设置选中Tab文字的颜色
        mPagerSlidingTabStrip.setSelectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.font_black));
        // 取消点击Tab时的背景色
        mPagerSlidingTabStrip.setTabBackground(0);
    }

    public static class AlivcFragmentPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragments;
        private ArrayList<String> mTitles;

        public AlivcFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> title) {
            super(fm);
            this.mFragments = fragments;
            this.mTitles = title;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }
}
