package com.innomalist.taxi.driver.activities.main.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.innomalist.taxi.common.models.Travel;
import com.innomalist.taxi.common.ui.ArrayFragmentPagerAdapter;
import com.innomalist.taxi.driver.activities.main.fragments.RequestFragment;

import java.util.ArrayList;

public class RequestsFragmentPagerAdapter extends ArrayFragmentPagerAdapter<Travel> {
    public RequestsFragmentPagerAdapter(FragmentManager fm, ArrayList<Travel> requests) {
        super(fm, requests);
    }

    public int getPositionWithTravelId(int travelId) {
        for (int i = 0; i < getCount(); i++) {
            if (getItem(i).getId() == travelId) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Fragment getFragment(Travel item, int position) {
        // return RequestCardFragment.newInstance(item);
        return RequestFragment.newInstance(item);
    }
}