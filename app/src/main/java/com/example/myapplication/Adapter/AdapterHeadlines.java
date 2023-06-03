package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.HeadlinesResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterHeadlines extends RecyclerView.Adapter<AdapterHeadlines.viewheadlines> {


    List<HeadlinesResponse> data;
    Context context;


    public AdapterHeadlines(List<HeadlinesResponse> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterHeadlines.viewheadlines onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.items_headlines, parent, false);
        return new viewheadlines(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHeadlines.viewheadlines holder, int position) {

        String title = data.get(position).getTitle();
        String[] words = title.split("\\s+");
        String truncatedTitle = "";

        if (words.length <= 6) {
            truncatedTitle = title;
        } else {
            for (int i = 0; i < 6; i++) {
                truncatedTitle += words[i] + " ";
            }
        }

        holder.text1.setText(truncatedTitle.trim());
        holder.text2.setText(String.valueOf(data.get(position).getAuthor()));
//        holder.text3.setText(String.valueOf(data.get(position).getPublishedAt()));

        Picasso.with(context)
                .load(data.get(position).getUrlToImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewheadlines extends RecyclerView.ViewHolder {
        TextView text1,text2,text3;
        ImageView imageView;
        public viewheadlines(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.title_hideline);
            text2 = itemView.findViewById(R.id.headline_auth);
            text3 = itemView.findViewById(R.id.headline_created);
            imageView = itemView.findViewById(R.id.headline_img);
        }
    }
}
