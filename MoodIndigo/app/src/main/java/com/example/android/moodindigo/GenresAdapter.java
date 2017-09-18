package com.example.android.moodindigo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moodindigo.data.GenresResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais on 30/07/17.
 */

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.myViewHolder> {

    private List<GenresResponse> responses;

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

    public GenresAdapter(List<GenresResponse> responses){
        this.responses=responses;
    }

    @Override
    public  GenresAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_genres, parent,false);
        myViewHolder vh = new myViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position){
        holder.title.setText(responses.get(position).getName());
        holder.description.setText(responses.get(position).getDescription());
    }

    @Override
    public int getItemCount(){
        return responses.size();
    }
}

