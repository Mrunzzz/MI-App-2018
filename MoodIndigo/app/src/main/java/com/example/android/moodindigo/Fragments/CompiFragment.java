package com.example.android.moodindigo.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moodindigo.R;

/**
 * Created by owais on 05/07/17.
 */

public class CompiFragment extends Fragment {
    public CompiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_compi, container, false);
    }
}
