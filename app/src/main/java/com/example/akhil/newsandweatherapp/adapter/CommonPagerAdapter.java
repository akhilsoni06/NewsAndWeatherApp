package com.example.akhil.newsandweatherapp.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.akhil.newsandweatherapp.view.FavoriteFragment;
import com.example.akhil.newsandweatherapp.view.NewsListFragment;
import java.util.List;

public class CommonPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTabList;

    public CommonPagerAdapter(FragmentManager fm, List<String> tabList) {
        super(fm);
        mTabList = tabList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new NewsListFragment();
        }

        if (position == 1) {
            fragment = new FavoriteFragment();
        }

        return fragment;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }
}
