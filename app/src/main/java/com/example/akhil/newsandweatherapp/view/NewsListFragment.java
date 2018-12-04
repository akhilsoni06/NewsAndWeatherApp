package com.example.akhil.newsandweatherapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.akhil.newsandweatherapp.database.AppDatabase;
import com.example.akhil.newsandweatherapp.interactor.ItemFavoriteListener;
import com.example.akhil.newsandweatherapp.model.Article;
import com.example.akhil.newsandweatherapp.model.NewsItem;
import com.example.akhil.newsandweatherapp.viewmodel.NewsViewModel;

import java.util.List;

public class NewsListFragment extends Fragment implements ItemFavoriteListener {

    private static final String TAG = NewsListFragment.class.getSimpleName();

    private RecyclerView mRecycleListView;
    private ProgressBar mProgressBar;
    private NewsViewModel mViewModel;
    private RecycleViewAdapter mListAdapter;
    private AppDatabase mDataBase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBase = Room.databaseBuilder(getActivity(), AppDatabase.class, AppDatabase.DB_NAME).build();
    }

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

    private void setAdapter(List<Article> articleList) {
        if (articleList != null && articleList.size() > 0) {
            mListAdapter = new RecycleViewAdapter(this, articleList);
            mRecycleListView.setAdapter(mListAdapter);
        }
    }

    @Override
    public void onItemFavoriteClicked(Article article) {
        new SaveArticle().execute(article);
    }

    private class SaveArticle extends AsyncTask<Article, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Article... param) {
            Article article = param[0];
            if (article != null) {
                mDataBase.getNewsItemDao().insertAll(article);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
