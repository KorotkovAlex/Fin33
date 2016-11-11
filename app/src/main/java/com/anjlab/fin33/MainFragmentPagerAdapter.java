package com.anjlab.fin33;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.anjlab.fin33.model.ExchangeRate;

/**
 * Created by Саня on 19.10.2016.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Лучший курс", "USD", "EUR"};
    private Context context;

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MainFragment();
        } else if (position == 1) {
            return ExchangeRateFragment.newInstance(ExchangeRate.Currency.USD);

        } else {
            return ExchangeRateFragment.newInstance(ExchangeRate.Currency.EUR);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
