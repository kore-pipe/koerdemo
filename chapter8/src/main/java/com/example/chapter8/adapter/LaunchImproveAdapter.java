package com.example.chapter8.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chapter8.fragment.LaunchFragment;

public class LaunchImproveAdapter extends FragmentPagerAdapter {
    private int[] mImageArray;

    public LaunchImproveAdapter(FragmentManager fm, int[] imageArray) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mImageArray = imageArray;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return LaunchFragment.newInstance(mImageArray.length,position,mImageArray[position]);
    }

    @Override
    public int getCount() {
        return mImageArray.length;
    }
}
