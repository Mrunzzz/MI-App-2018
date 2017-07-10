package com.example.android.moodindigo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.moodindigo.Fragments.CompiFragment;
import com.example.android.moodindigo.Fragments.FAQFragment;
import com.example.android.moodindigo.Fragments.NewsFragment;

/**
 * Created by owais on 05/07/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs; // number of tab variable for viewPager

    //PagerAdapter Constructor
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                CompiFragment compiFragment = new CompiFragment();
                return compiFragment;
            case 1:
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;
            case 2:
                FAQFragment faqFragment = new FAQFragment();
                return faqFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
