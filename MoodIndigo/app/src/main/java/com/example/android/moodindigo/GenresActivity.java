package com.example.android.moodindigo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.moodindigo.data.EventsResponse;
import com.example.android.moodindigo.data.GenresResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GenresAdapter adapter;
    String receivedString;
    List<GenresResponse> competitionsResponseList = new ArrayList<>();
    List<GenresResponse> concertsResponseList = new ArrayList<>();
    List<GenresResponse> informalsResponseList = new ArrayList<>();
    List<GenresResponse> proshowsResponseList = new ArrayList<>();
    List<GenresResponse> workshopsResponseList = new ArrayList<>();
    List<GenresResponse> artsAndIdeasResponseList = new ArrayList<>();
    RetrofitClass rcinitiate;
    SearchInterface client;
    ProgressBar progressBar;
    List<GenresResponse> responses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        progressBar=findViewById(R.id.pb_loading);
        Bundle bundle = getIntent().getExtras();

        receivedString = bundle.getString("Type");
        Log.d("rec",receivedString);


        rcinitiate = new RetrofitClass(GenresActivity.this);

        client = rcinitiate.createBuilder().create(SearchInterface.class);

        rcinitiate.startLogging();

        Call<EventsResponse> call = client.getEvents();
        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {

                EventsResponse eventsResponse = response.body();
                competitionsResponseList = eventsResponse.getCompetitions();
                Log.i("response", competitionsResponseList.get(0).getName());
                proshowsResponseList = eventsResponse.getProshows();
                Log.i("response2", proshowsResponseList.get(0).getName());
                informalsResponseList = eventsResponse.getInformals();
                workshopsResponseList = eventsResponse.getWorkshops();
                concertsResponseList = eventsResponse.getConcerts();
                artsAndIdeasResponseList = eventsResponse.getArtsAndIdeas();

                if (receivedString.equals("competitions"))
                    responses = competitionsResponseList;
                else if (receivedString.equals("concerts"))
                    responses = concertsResponseList;
                else if (receivedString.equals("proshows"))
                    responses = proshowsResponseList;
                else if (receivedString.equals("informals"))
                    responses = informalsResponseList;
                else if (receivedString.equals("workshops"))
                    responses = workshopsResponseList;
                else if (receivedString.equals("arts and ideas"))
                    responses = artsAndIdeasResponseList;

                progressBar.setVisibility(View.GONE);


                adapter = new GenresAdapter(responses,GenresActivity.this);
                recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {

            }

        });

    }

}

