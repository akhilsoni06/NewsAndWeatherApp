package com.example.akhil.newsandweatherapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.example.akhil.newsandweatherapp.adapter.CommonPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class CommonPagerFragment extends BasePagerFragment implements ViewPager.OnPageChangeListener {

    private List<String> mTabList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //mActionBarListener.setActionBarTitle(getArguments().getString(ACTION_TITLE));

        mTabList = new ArrayList<>();
        mTabList.add("News");
        mTabList.add("Favorite");

        if (mTabList != null && !mTabList.isEmpty() && mTabList.size() < 3) {
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        }

        if (mTabList != null && !mTabList.isEmpty()) {
            mViewPager.setAdapter(new CommonPagerAdapter(getChildFragmentManager(), mTabList));
            mTabLayout.setupWithViewPager(mViewPager);
            mTabLayout.setSmoothScrollingEnabled(true);
            mViewPager.setCurrentItem(0);
            mViewPager.addOnPageChangeListener(this);
            //mItemClickListener.onTabSelected(mTabList.get(0));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
