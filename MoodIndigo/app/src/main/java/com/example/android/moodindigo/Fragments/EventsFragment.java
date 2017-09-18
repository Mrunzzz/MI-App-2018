package com.example.android.moodindigo.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.moodindigo.GridViewAdapter;
import com.example.android.moodindigo.R;


public class EventsFragment extends Fragment {



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

        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        GridViewAdapter gridViewAdapter=new GridViewAdapter(getContext());
        GridView gridView=new GridView(getContext());
        gridView=rootView.findViewById(R.id.gridview);

        gridView.setAdapter(gridViewAdapter);








        return rootView;

    }

}
