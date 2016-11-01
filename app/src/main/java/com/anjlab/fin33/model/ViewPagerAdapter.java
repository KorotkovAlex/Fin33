package com.anjlab.fin33.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitels = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragments(Fragment fragments, String titles) {
        this.fragments.add(fragments);
        this.tabTitels.add(titles);
    }

    @Override
    public Fragment getItem(int position) {

        Log.d("fragment.get(position)", "" + fragments.get(position));
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        Log.d("fragment.get(position)", "" + fragments.size());
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Log.d("fragment.get(position)", "" + tabTitels.get(position));
        return tabTitels.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}