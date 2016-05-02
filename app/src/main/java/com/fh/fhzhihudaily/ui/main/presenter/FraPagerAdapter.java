package com.fh.fhzhihudaily.ui.main.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fh.fhzhihudaily.ui.main.bean.InfoBean;
import com.fh.fhzhihudaily.ui.main.view.fragment.HomePageFragment1;

import java.util.List;

/**
 * Created by Jason.fang on 2016/4/28.
 */
public class FraPagerAdapter extends FragmentStatePagerAdapter {
    List<InfoBean.TopStoriesBean> data;
    public FraPagerAdapter(FragmentManager fm, List<InfoBean.TopStoriesBean> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return HomePageFragment1.newInstance(data.get(position));
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
