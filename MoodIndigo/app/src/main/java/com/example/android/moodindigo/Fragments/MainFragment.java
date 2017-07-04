package com.example.android.moodindigo.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moodindigo.R;


public class MainFragment extends Fragment {

    //Mrunmayi
//    View view;
//    ViewPager viewPager;
//    ViewPagerAdapter viewPagerAdapter;
    //Mrunmayi
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_main, container,false);

        //Mrunmayi
//        view=inflater.inflate(R.layout.fragment_main, container, false);
//        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//        tabLayout.addTab(tabLayout.newTab().setText("News"));
//        tabLayout.addTab(tabLayout.newTab().setText("Competitions"));
//
//        viewPager = (ViewPager) view.findViewById(R.id.container);
//        viewPager.setAdapter(new MainFragment.ViewPagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        return view;
//    }
//
//    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
//        int mNumOfTabs;
//
//        public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
//            super(fm);
//            this.mNumOfTabs = NumOfTabs;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//
//            switch (position) {
//                case 0:
//                    NewsFragment tab1 = new NewsFragment();
//                    return tab1;
//                case 1:
//                    CompiFragment tab2 = new CompiFragment();
//                    return tab2;
//                default:
//                    return null;
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return mNumOfTabs;
//        }
//    }

        //Mrunmayi



    }
}


