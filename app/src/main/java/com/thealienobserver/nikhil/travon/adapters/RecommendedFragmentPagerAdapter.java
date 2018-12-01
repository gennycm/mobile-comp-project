package com.thealienobserver.nikhil.travon.controllers.RecommendedPlaces;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RecommendedFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Attractions", "Hospitals", "Universities","Restaurants"};
    private String city;

    RecommendedFragmentPagerAdapter(FragmentManager fm, String city) {
        super(fm);
        this.city = city;
    }

    @Override
    public Fragment getItem(int position) {

        return RecommendedPlacesFragment.newInstance(position + 1, city);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
