package com.example.android.moodindigo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.view.ViewParent;
import com.example.android.moodindigo.R;
import com.example.android.moodindigo.PagerAdapter;
import com.example.android.moodindigo.data.NewsResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais on 05/07/17.
 */




public class MainFragment extends Fragment {

    View view;
    ViewPager viewPager;
    private FragmentActivity myContext;
    int size;
    List<NewsResponse> responses=new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();

        view=inflater.inflate(R.layout.fragment_main, container, false);

        Bundle bundle=getArguments();
        size=bundle.getInt("size");
        Log.d("size223", String.valueOf(size));
        for(int j=0;j<size;j++){
            NewsResponse newsResponse=new Gson().fromJson(bundle.getString("List" + j),NewsResponse.class);
            Log.d("name",newsResponse.getName());
            responses.add(newsResponse);
        }

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Multicity"));
        tabLayout.addTab(tabLayout.newTab().setText("Events"));

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        Log.d("size223", String.valueOf(responses.size()));
        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount(),responses));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity) context;
        super.onAttach(context);

    }
}
