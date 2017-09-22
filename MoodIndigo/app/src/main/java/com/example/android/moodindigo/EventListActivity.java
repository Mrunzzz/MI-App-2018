package com.example.android.moodindigo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.moodindigo.data.EventDetailResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EventsAdapter eventsAdapter;
    List<EventDetailResponse> responses=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        recyclerView=findViewById(R.id.recyclerview);

        Bundle bundle=getIntent().getExtras();

        int size=bundle.getInt("size");

        for(int j=0;j<size;j++){
            String responsejson=bundle.getString("List"+j);
            responses.add(new Gson().fromJson(responsejson,EventDetailResponse.class));
        }


        eventsAdapter=new EventsAdapter(responses);
        recyclerView.setAdapter(eventsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




    }
}
