package com.fh.fhzhihudaily.ui.main.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fh.fhzhihudaily.R;
import com.fh.fhzhihudaily.ui.base.BaseActivity;
import com.fh.fhzhihudaily.ui.base.BaseOnScrollListener;
import com.fh.fhzhihudaily.ui.main.model.HomeListModel;
import com.fh.fhzhihudaily.ui.main.presenter.HomeAdapter;
import com.fh.fhzhihudaily.ui.main.presenter.StartPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 *
 * Created by Jason.fang on 2016/4/27.
 */
public class StartActivity extends BaseActivity implements StartPresenter.IStartView {

    @Bind(R.id.homepage_recyclerview)
    RecyclerView recyclerView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    StartPresenter startPresenter;

    private HomeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("首页");
        startPresenter = new StartPresenter(this, this);
        startPresenter.onCreate();
        LinearLayoutManager ll = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(ll);
        adapter = new HomeAdapter(new ArrayList<HomeListModel>());
        recyclerView.setAdapter(adapter);
        startPresenter.requestNet();

        recyclerView.addOnScrollListener(new BaseOnScrollListener(this, startPresenter, adapter, this));
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                startPresenter.requestLatest();
            }
        });
    }


    @Override
    public void getLatest(List<HomeListModel> homeLists) {
        adapter.clear();
        adapter.addAll(homeLists);
    }

    @Override
    public void getMoreList(List<HomeListModel> homeListModels) {
        adapter.addAll(homeListModels);
    }

}
