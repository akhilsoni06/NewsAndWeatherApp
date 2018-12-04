package com.example.akhil.newsandweatherapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.akhil.newsandweatherapp.model.Article;
import com.example.akhil.newsandweatherapp.model.NewsItem;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Insert
    void insertAll(Article... newsItem);

    @Update
    void updateAll(Article... newsItem);

    @Delete
    void deleteAll(Article... newsItem);

    @Query("SELECT * FROM news")
    List<Article> getAllNewsList();
}
