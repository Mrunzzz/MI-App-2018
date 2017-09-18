package com.example.android.moodindigo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.android.moodindigo.data.GenresResponse;
import com.example.android.moodindigo.data.EventsResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mrunz on 17/9/17.
 */

public class GridViewAdapter extends BaseAdapter {
    List<GenresResponse> competitionsResponseList=new ArrayList<>();
    List<GenresResponse> concertsResponseList=new ArrayList<>();
    List<GenresResponse> informalsResponseList=new ArrayList<>();
    List<GenresResponse> proshowsResponseList=new ArrayList<>();
    List<GenresResponse> workshopsResponseList=new ArrayList<>();
    List<GenresResponse> artsAndIdeasResponseList=new ArrayList<>();
    RetrofitClass rcinitiate;
    SearchInterface client;
    public int pull=1;
    private List<Item> genres=new ArrayList<>();
    private Context context;

    public GridViewAdapter(Context context) {
        genres.add(new Item("Competitions", R.drawable.ic_menu_compi));
        genres.add(new Item("Concerts", R.drawable.ic_menu_compi));
        genres.add(new Item("Informals", R.drawable.ic_menu_compi));
        genres.add(new Item("Proshows", R.drawable.ic_menu_compi));
        genres.add(new Item("Workshops", R.drawable.ic_menu_compi));
        genres.add(new Item("Arts and Ideas", R.drawable.ic_menu_compi));
        this.context = context;

    }





    @Override
    public int getCount() {
        return genres.size();
    }

    @Override
    public Object getItem(int i) {
        return genres.get(i);
    }

    @Override
    public long getItemId(int i) {
        return genres.get(i).drawableid;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        view=inflater.inflate(R.layout.grid_item,viewGroup,false);

        LinearLayout linearLayout;
        TextView name;
        ImageView image;
        linearLayout=view.findViewById(R.id.linearlayout);
        name=view.findViewById(R.id.genre_name);
        image=view.findViewById(R.id.genre_image);

        name.setText(genres.get(i).name);
        image.setImageResource(genres.get(i).drawableid);



        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    rcinitiate=new RetrofitClass(context);

                    client=rcinitiate.createBuilder().create(SearchInterface.class);

                    rcinitiate.startLogging();

                    Call<EventsResponse> call=client.getEvents();

                    call.enqueue(new Callback<EventsResponse>() {
                        @Override
                        public void onResponse(Call< EventsResponse > call, Response<EventsResponse> response) {

                            EventsResponse eventsResponse = response.body();
                            competitionsResponseList = eventsResponse.getCompetitions();
                            Log.i("response",competitionsResponseList.get(0).getName());
                            proshowsResponseList = eventsResponse.getProshows();
                            Log.i("response2",proshowsResponseList.get(0).getName());
                            informalsResponseList = eventsResponse.getInformals();
                            workshopsResponseList = eventsResponse.getWorkshops();
                            concertsResponseList = eventsResponse.getConcerts();
                            artsAndIdeasResponseList = eventsResponse.getArtsAndIdeas();
                            Intent intent=new Intent(context,GenresActivity.class);
                            Log.i("check", String.valueOf(competitionsResponseList.size()));

                            switch (i)
                            {
                                case 0: String responselist= new Gson().toJson(competitionsResponseList);
                                    intent.putExtra("List",responselist);
                                    Log.i("send",responselist);
                                    context.startActivity(intent);
                                case 1: responselist= new Gson().toJson(concertsResponseList);
                                    intent.putExtra("List",responselist);
                                    context.startActivity(intent);
                                case 2: responselist= new Gson().toJson(informalsResponseList);
                                    intent.putExtra("List",responselist);
                                    context.startActivity(intent);
                                case 3:  responselist= new Gson().toJson(proshowsResponseList);
                                    intent.putExtra("List",responselist);
                                    context.startActivity(intent);
                                case 4: responselist= new Gson().toJson(workshopsResponseList);
                                    intent.putExtra("List",responselist);
                                    context.startActivity(intent);
                                case 5:  responselist= new Gson().toJson(artsAndIdeasResponseList);
                                    intent.putExtra("List",responselist);
                                    context.startActivity(intent);


                            }

                        }
                        @Override
                        public void onFailure(Call<EventsResponse> call, Throwable t) {

                        }
                    });








                    }


        });
        return view;
    }

    private static class Item{
        public String name;
        public int drawableid;

        public Item(String name,int drawableid){
            this.name=name;
            this.drawableid=drawableid;
        }

    }


}
