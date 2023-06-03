package com.example.myapplication.Config;

import com.example.myapplication.model.Headlines;
import com.example.myapplication.model.HeadlinesResponse;
import com.example.myapplication.model.News;
import com.example.myapplication.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interfaces {
    @GET("v2/top-headlines")
    Call<Headlines> getTopHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("v2/everything")
    Call<News> getTopNews(
            @Query("domains") String domains,
            @Query("apiKey") String apiKey
    );
}
