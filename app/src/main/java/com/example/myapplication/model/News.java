package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("articles")
    @Expose
    private List<NewsResponse> newsResponses = null;

    public List<NewsResponse> getNewsResponses() {
        return newsResponses;
    }

    public void setNewsResponses(List<NewsResponse> newsResponses) {
        this.newsResponses = newsResponses;
    }
}
