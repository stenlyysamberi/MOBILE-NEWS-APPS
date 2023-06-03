package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterHeadlines;
import com.example.myapplication.Adapter.AdapterNews;
import com.example.myapplication.Config.ApiClient;
import com.example.myapplication.Config.Interfaces;
import com.example.myapplication.model.Headlines;
import com.example.myapplication.model.HeadlinesResponse;
import com.example.myapplication.model.News;
import com.example.myapplication.model.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
   String apiKey;
   RecyclerView headlines,news;
   private AdapterHeadlines adapterHeadlines;
   private AdapterNews adapterNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiKey = "f7057319d406441e8317dae87edfd1be";
        headlines();
        everything();
    }
    void headlines(){
        try {
            Interfaces endpoint = ApiClient.getRetrofitInstance().create(Interfaces.class);
            Call<Headlines> topHeadlines = endpoint.getTopHeadlines("US",apiKey);
            topHeadlines.enqueue(new Callback<Headlines>() {
                @Override
                public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                    List<HeadlinesResponse> x = response.body().getHealines();
                    Context context = MainActivity.this;
                    if (response.isSuccessful() && response.body()!=null){
//                        for (HeadlinesResponse headline : x) {
//                            Log.d("Headline", "Author: " + headline.getAuthor());
//                        }
                        headlines = findViewById(R.id.recyheadlines);
                        headlines.setEnabled(false);
                        adapterHeadlines = new AdapterHeadlines(x, context);
                        headlines.setAdapter(adapterHeadlines);
                        headlines.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL, false));
                    }else{
                        Log.e("error", response.errorBody().toString());

                    }
                }
                @Override
                public void onFailure(Call<Headlines> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "response" + t, Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Headlines" + e, Toast.LENGTH_SHORT).show();
            Log.e("Headlines", String.valueOf(e));
        }
    }

    void everything(){
        try {
            Interfaces endpoint = ApiClient.getRetrofitInstance().create(Interfaces.class);
            Call<News> topHeadlines = endpoint.getTopNews("wsj.com",apiKey);
            topHeadlines.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    List<NewsResponse> x = response.body().getNewsResponses();
                    Context context = MainActivity.this;
                    if (response.isSuccessful() && response.body()!=null){
                        news = findViewById(R.id.recynews);
                        news.setEnabled(false);
                        adapterNews = new AdapterNews(x, context);
                        news.setAdapter(adapterNews);
                        news.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL, false));
                    }else{
                        Log.e("error", response.errorBody().toString());
                    }
                }
                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "response" + t, Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Headlines" + e, Toast.LENGTH_SHORT).show();
            Log.e("Headlines", String.valueOf(e));
        }
    }
}