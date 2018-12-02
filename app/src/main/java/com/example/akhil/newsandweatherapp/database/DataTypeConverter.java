package com.example.akhil.newsandweatherapp.database;

import android.arch.persistence.room.TypeConverter;

import com.example.akhil.newsandweatherapp.model.NewsItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DataTypeConverter {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<NewsItem.Article> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<NewsItem.Article>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<NewsItem.Article> someObjects) {
        return gson.toJson(someObjects);
    }
}