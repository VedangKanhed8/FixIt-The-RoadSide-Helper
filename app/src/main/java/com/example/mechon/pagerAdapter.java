package com.example.mechon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

@SuppressWarnings("ALL")
public class pagerAdapter extends FragmentPagerAdapter {
    private int numoftabs;
    public pagerAdapter(@NonNull FragmentManager fm,int numoftabs) {
        super(fm);
        this.numoftabs=numoftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new pendMechorders();
            case 1:
                return new compMechOrd();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
