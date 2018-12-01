package com.example.akhil.newsandweatherapp.interactor;

import com.example.akhil.newsandweatherapp.model.NewsItem;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("top-headlines")
    Call<NewsItem> getNewsList(@QueryMap Map<String, String> map);
}
