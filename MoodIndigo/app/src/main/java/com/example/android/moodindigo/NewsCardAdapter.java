package com.example.android.moodindigo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by owais on 30/07/17.
 */

public class NewsCardAdapter extends RecyclerView.Adapter<NewsCardAdapter.myViewHolder> {

    private ArrayList<NewsCard> news;

    public static class myViewHolder extends RecyclerView.ViewHolder{
        public TextView title, description;
        public CardView mCardView;
        public myViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.card_title);
            description = (TextView) view.findViewById(R.id.card_description);
            mCardView = (CardView) view.findViewById(R.id.news_card_view);
        }
    }

    public NewsCardAdapter(ArrayList<NewsCard> news1){
        news = news1;
    }

    @Override
    public  NewsCardAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.news_card, parent,false);
        myViewHolder vh = new myViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position){
        holder.title.setText(news.get(position).getTitleOfCard());
        holder.description.setText(news.get(position).getDescriptionOfCard());
    }

    @Override
    public int getItemCount(){
        return news.size();
    }
}

