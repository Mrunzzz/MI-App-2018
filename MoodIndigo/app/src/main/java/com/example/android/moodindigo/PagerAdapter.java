package com.example.android.moodindigo;

/**
 * Created by owais on 04/07/17.
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.moodindigo.Fragments.CompiFragment;
import com.example.android.moodindigo.Fragments.MainFragment;
import com.example.android.moodindigo.Fragments.NewsFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MainFragment tab1 = new MainFragment();
                return tab1;
            case 1:
                NewsFragment tab2 = new NewsFragment();
                return tab2;
            case 2:
                CompiFragment tab3 = new CompiFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
