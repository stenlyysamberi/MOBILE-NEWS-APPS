package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Headlines {
    @SerializedName("articles")
    @Expose
    private List<HeadlinesResponse> healines = null;

    public List<HeadlinesResponse> getHealines() {
        return healines;
    }

    public void setHealines(List<HeadlinesResponse> healines) {
        this.healines = healines;
    }
}
