package com.example.android.moodindigo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moodindigo.data.EventDetailResponse;
import com.example.android.moodindigo.data.GenresResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mrunz on 22/9/17.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.myViewHolder>{
    private List<EventDetailResponse> responses;
    int s=0,r=0,p=0;


    public static class myViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView description;
        public TextView prizes;
        public TextView rules;
        public TextView subtitle;
        public ImageView open;
        public ImageView close;
        public LinearLayout linearLayout;
        public myViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
           open=itemView.findViewById(R.id.open);
            description=itemView.findViewById(R.id.description);
            rules=itemView.findViewById(R.id.rules);
            subtitle=itemView.findViewById(R.id.subtitle);
            linearLayout=itemView.findViewById(R.id.layout);
            prizes=itemView.findViewById(R.id.prizes);
            close=itemView.findViewById(R.id.close);

        }




    }

    public EventsAdapter(List<EventDetailResponse> responses){
        this.responses=responses;
    }

    @Override
    public  EventsAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.eventitem, parent,false);
        EventsAdapter.myViewHolder vh = new EventsAdapter.myViewHolder(itemView);



        return vh;
    }

    @Override
    public void onBindViewHolder(final EventsAdapter.myViewHolder holder, int position){
        holder.name.setText(responses.get(position).getName());
        Log.d("log1",responses.get(0).getName());
        holder.description.setText("DESCRIPTIONS: " +responses.get(position).getDescription());
        if(responses.get(position).getPrizes()!=null)
        {p=1;
        holder.prizes.setText("PRIZES: "+responses.get(position).getPrizes());}
        if(responses.get(position).getSubtitle()!=null)    {
        holder.subtitle.setText("SUBTITLE: "+responses.get(position).getSubtitle());
        s=1;}
        if(responses.get(position).getRules()!=null){
            r=1;
        holder.rules.setText("RULES: "+responses.get(position).getRules());}
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.description.getVisibility()==View.VISIBLE)
                {
                    holder.open.setVisibility(View.VISIBLE);
                    holder.close.setVisibility(View.GONE);
                    holder.description.setVisibility(View.GONE);
                    holder.rules.setVisibility(View.GONE);
                    holder.prizes.setVisibility(View.GONE);
                    holder.subtitle.setVisibility(View.GONE);
                }
                else if(holder.description.getVisibility()==View.GONE){
                    holder.open.setVisibility(View.GONE);
                    holder.close.setVisibility(View.VISIBLE);
                    holder.description.setVisibility(View.VISIBLE);
                    if(r==1)
                    holder.rules.setVisibility(View.VISIBLE);
                    if(p==1)
                    holder.prizes.setVisibility(View.VISIBLE);
                    if (s==1)
                    holder.subtitle.setVisibility(View.VISIBLE);
                }
            }

        });
    }

    @Override
    public int getItemCount(){
        return responses.size();
    }
}
