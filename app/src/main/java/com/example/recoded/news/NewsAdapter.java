package com.example.recoded.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<MyViewHolder> {


    Context c;// is needed to generate a recyclerview

    ArrayList<News> Newss ;// the plural form of news

    public NewsAdapter(Context c, ArrayList<News> Newss) {
        this.c = c;

        this.Newss = Newss;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(c).inflate(R.layout.itemnews, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //current News
        News N = Newss.get(position);
        //bind Data
        holder.NewsTitle.setText(N.getmNewsTitle());
        holder.NewsText.setText(N.getmNewsText());
        holder.NewsWriter.setText(N.getmNewsWriter());
        holder.NewsPublishDate.setText(N.getmNewsPublishDate());

        Picasso.with(c).load(N.getNewsImageResourceId()).into(holder.NewsImage);// this to load the image from url we parse.
        //holder.NewsImageResourceId.setImageResource(N.getNewsImageResourceId());
    }

    @Override
    public int getItemCount() {
        if (Newss != null) {
            return Newss.size();
       }
//
return 0;
//    }
        }

    }