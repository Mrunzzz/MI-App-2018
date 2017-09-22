package com.example.android.moodindigo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moodindigo.data.EventDetailResponse;
import com.example.android.moodindigo.data.GenresResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais on 30/07/17.
 */

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.myViewHolder> {

    private List<GenresResponse> responses;
    private Context context;

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

    public GenresAdapter(List<GenresResponse> responses,Context context){
        this.responses=responses;
        this.context=context;
    }

    @Override
    public  GenresAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.news_card, parent,false);
        myViewHolder vh = new myViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position){
        holder.title.setText(responses.get(position).getName());
        holder.description.setText(responses.get(position).getDescription());
        final List<EventDetailResponse> eventDetailResponses=responses.get(position).getEvents();
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,EventListActivity.class);

                intent.putExtra("size",eventDetailResponses.size());
                Log.d("size", String.valueOf(eventDetailResponses.size()));
                for(int i=0;i<eventDetailResponses.size();i++){
                    String responsejson=new Gson().toJson(eventDetailResponses.get(i));
                    intent.putExtra("List"+i,responsejson);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return responses.size();
    }
}

