package com.example.akhil.newsandweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsItem {

    @SerializedName("status")
    public String status;

    @SerializedName("totalResults")
    public int totalResults;

    @SerializedName("articles")
    public List<Article> articleList;

    public class Article {
        public String author;
        public String title;
        public String description;
        public String url;
        public String urlToImage;
        public String publishedAt;
        public String content;
    }

}
