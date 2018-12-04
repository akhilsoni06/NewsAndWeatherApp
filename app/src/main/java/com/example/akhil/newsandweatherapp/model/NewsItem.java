package com.example.akhil.newsandweatherapp.model;

import android.arch.persistence.room.TypeConverters;
import com.example.akhil.newsandweatherapp.database.DataTypeConverter;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class NewsItem {

    @SerializedName("status")
    public String status;

    @SerializedName("totalResults")
    public int totalResults;

    @SerializedName("articles")
    @TypeConverters(DataTypeConverter.class)
    public List<Article> articleList;
}
