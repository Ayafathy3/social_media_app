package com.example.android.socialmedia.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.socialmedia.Fragment.DataFragment;
import com.example.android.socialmedia.Fragment.HomeFragment;
import com.example.android.socialmedia.Fragment.ProfileFragment;


public class AdapterFrag extends FragmentPagerAdapter {

    public AdapterFrag( FragmentManager fm ) {
        super(fm);
    }

    @Override
    public Fragment getItem( int position ) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new DataFragment();
            case 2:
                return new ProfileFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
