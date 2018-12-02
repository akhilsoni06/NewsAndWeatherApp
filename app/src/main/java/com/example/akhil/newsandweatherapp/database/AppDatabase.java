package com.example.akhil.newsandweatherapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.akhil.newsandweatherapp.database.dao.NewsItemDao;
import com.example.akhil.newsandweatherapp.model.NewsItem;

@Database(entities = {NewsItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "news_db";

    public abstract NewsItemDao getNewsItemDao();

}
