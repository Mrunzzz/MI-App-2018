package com.example.android.moodindigo;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.moodindigo.data.GenresResponse;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenresActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GenresAdapter adapter;
    List<GenresResponse> responses=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        Bundle bundle=getIntent().getExtras();

            String responsejson =bundle.getString("List");

        Log.i("rec",responsejson);

        GenresResponse[] responses1= new Gson().fromJson(responsejson, GenresResponse[].class);

            responses=new ArrayList<GenresResponse>(Arrays.asList(responses1));
            //Log.i("Recieved",responses.get(0).getName());


        adapter=new GenresAdapter(responses);


        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
