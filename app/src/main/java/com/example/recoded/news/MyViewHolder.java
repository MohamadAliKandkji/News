package com.example.recoded.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView NewsTitle;
    TextView NewsText;
    TextView NewsWriter;
    TextView NewsPublishDate;
    ImageView NewsImage;//image of our news
     // ImageView NewsImageResourceId;


    public MyViewHolder(View itemnews) {
        super(itemnews);
        NewsTitle = (TextView) itemnews.findViewById(R.id.newstitle);
        NewsText = (TextView) itemnews.findViewById(R.id.newsdescription);
        NewsWriter = (TextView) itemnews.findViewById(R.id.news_writer);
        NewsPublishDate = (TextView) itemnews.findViewById(R.id.news_publish_date);
        NewsImage = (ImageView) itemnews.findViewById(R.id.newsimage);

     //   NewsImageResourceId = (ImageView) itemnews.findViewById(R.id.newsimage);

    }
}





