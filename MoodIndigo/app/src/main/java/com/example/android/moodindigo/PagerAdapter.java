package com.example.android.moodindigo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.example.android.moodindigo.Fragments.CompiFragment;
import com.example.android.moodindigo.Fragments.EventsFragment;
import com.example.android.moodindigo.Fragments.FAQFragment;
import com.example.android.moodindigo.Fragments.NewzFragment;

/**
 * Created by owais on 05/07/17.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs; // number of tab variable for viewPager


    //PagerAdapter Constructor
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);

        this.mNumOfTabs = NumOfTabs;


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                NewzFragment newzFragment = new NewzFragment();
                return newzFragment;

            case 1:
                CompiFragment compiFragment = new CompiFragment();
                return compiFragment;
            case 2:
                EventsFragment eventsFragment=new EventsFragment();
                return eventsFragment;
            default:
                return null;
        }
    }


}
