package com.example.akhil.newsandweatherapp.interactor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.akhil.newsandweatherapp.model.NewsItem;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    private final String TAG = RestApiClient.class.getSimpleName();
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "14165e21bd884fa8b0ece65871508823";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        synchronized (RestApiClient.class) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).
                        build();
            }
        }
        return retrofit;
    }


    public LiveData<NewsItem> getActorList() {
        final MutableLiveData<NewsItem> liveData = new MutableLiveData<>();
        ApiInterface apiInterface = RestApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("sources", "bbc-news");
        map.put("apiKey", API_KEY);

        apiInterface.getNewsList(map).enqueue(new Callback<NewsItem>() {
            @Override
            public void onResponse(Call<NewsItem> call, Response<NewsItem> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NewsItem> call, Throwable t) {
            }
        });

        return liveData;
    }

}

