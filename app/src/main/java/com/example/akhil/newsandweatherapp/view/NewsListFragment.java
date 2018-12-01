package com.example.akhil.newsandweatherapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.akhil.newsandweatherapp.R;
import com.example.akhil.newsandweatherapp.adapter.RecycleViewAdapter;
import com.example.akhil.newsandweatherapp.model.NewsItem;
import com.example.akhil.newsandweatherapp.viewmodel.NewsViewModel;

import java.util.List;

public class NewsListFragment extends Fragment {

    private static final String TAG = NewsListFragment.class.getSimpleName();

    private RecyclerView mRecycleListView;
    private ProgressBar mProgressBar;
    private NewsViewModel mViewModel;
    private RecycleViewAdapter mListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycle_view_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecycleListView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycleListView.setLayoutManager(manager);

        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mViewModel.getTopActorList().observe(this, new Observer<NewsItem>() {
            @Override
            public void onChanged(@Nullable NewsItem newsItem) {
                if (newsItem != null) {
                    setAdapter(newsItem.articleList);
                    Log.d(TAG, "actor size=" + newsItem.articleList.size());
                }

                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setAdapter(List<NewsItem.Article> articleList) {
        if (articleList != null && articleList.size() > 0) {
            mListAdapter = new RecycleViewAdapter(articleList);
            mRecycleListView.setAdapter(mListAdapter);
        }
    }

}
