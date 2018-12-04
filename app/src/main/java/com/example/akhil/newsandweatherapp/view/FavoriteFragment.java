package com.example.akhil.newsandweatherapp.view;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.akhil.newsandweatherapp.R;
import com.example.akhil.newsandweatherapp.adapter.RecycleViewAdapter;
import com.example.akhil.newsandweatherapp.database.AppDatabase;
import com.example.akhil.newsandweatherapp.model.Article;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private RecyclerView mRecycleListView;
    private ProgressBar mProgressBar;
    private AppDatabase mDataBase;
    private RecycleViewAdapter mListAdapter;

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
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       loadNewsResult();
    }

    /**
     * This method load the user info from the user table.
     */
   private void loadNewsResult() {
        new AsyncTask<Void, Void, List<Article>>() {
            @Override
            protected List<Article> doInBackground(Void... params) {
                return mDataBase.getNewsItemDao().getAllNewsList();
            }

            @Override
            protected void onPostExecute(List<Article> newsItem) {
                if ( newsItem != null && newsItem.size() > 0) {
                    mListAdapter = new RecycleViewAdapter(null,newsItem);
                    mRecycleListView.setAdapter(mListAdapter);
                }
                mProgressBar.setVisibility(View.GONE);
            }
        }.execute();
    }
}
