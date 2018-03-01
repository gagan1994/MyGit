package com.example.gagan.myexampleproject.uiadapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gagan.myexampleproject.fragments.BasePagerFragment;

import java.util.List;

/**
 * Created by Gagan on 3/1/2018.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private final List<BasePagerFragment> listOfFragment;

    public TabAdapter(FragmentManager fm, @NonNull List<BasePagerFragment> list) {
        super(fm);
        this.listOfFragment = list;
    }

    @Override
    public Fragment getItem(int position) {
        return listOfFragment.get(position);
    }

    @Override
    public int getCount() {
        return listOfFragment.size();
    }
}