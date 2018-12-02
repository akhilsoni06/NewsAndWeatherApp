package com.example.akhil.newsandweatherapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.akhil.newsandweatherapp.interactor.RestApiClient;
import com.example.akhil.newsandweatherapp.model.NewsItem;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private RestApiClient mRestApiClient;
    private MediatorLiveData<NewsItem> mMediatorLiveData;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        mRestApiClient = new RestApiClient();
        mMediatorLiveData = new MediatorLiveData<>();
    }

    public LiveData<NewsItem> getTopActorList() {
        mMediatorLiveData.addSource(mRestApiClient.getActorList(), new Observer<NewsItem>() {
            @Override
            public void onChanged(@Nullable NewsItem newsItem) {
                mMediatorLiveData.setValue(newsItem);
            }
        });
        return mMediatorLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
