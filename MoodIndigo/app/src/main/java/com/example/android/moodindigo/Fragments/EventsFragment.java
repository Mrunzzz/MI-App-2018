package com.example.android.moodindigo.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.moodindigo.GridViewAdapter;
import com.example.android.moodindigo.R;
import com.example.android.moodindigo.RetrofitClass;
import com.example.android.moodindigo.SearchInterface;
import com.example.android.moodindigo.data.EventsResponse;
import com.example.android.moodindigo.data.GenresResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventsFragment extends Fragment {


    List<GenresResponse> competitionsResponseList=new ArrayList<>();
    List<GenresResponse> concertsResponseList=new ArrayList<>();
    List<GenresResponse> informalsResponseList=new ArrayList<>();
    List<GenresResponse> proshowsResponseList=new ArrayList<>();
    List<GenresResponse> workshopsResponseList=new ArrayList<>();
    List<GenresResponse> artsAndIdeasResponseList=new ArrayList<>();
    RetrofitClass rcinitiate;
    SearchInterface client;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        rcinitiate = new RetrofitClass(getContext());

        client = rcinitiate.createBuilder().create(SearchInterface.class);

        rcinitiate.startLogging();

        Call<EventsResponse> call = client.getEvents();

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

                GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(),competitionsResponseList,concertsResponseList,informalsResponseList,proshowsResponseList,workshopsResponseList,artsAndIdeasResponseList);
                GridView gridView;
                gridView = rootView.findViewById(R.id.gridview);

                gridView.setAdapter(gridViewAdapter);


            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {

            }

        });
    return rootView;
    }
}
