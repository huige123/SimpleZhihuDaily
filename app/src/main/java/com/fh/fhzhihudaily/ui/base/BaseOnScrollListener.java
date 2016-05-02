package com.fh.fhzhihudaily.ui.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fh.fhzhihudaily.ui.main.presenter.HomeAdapter;
import com.fh.fhzhihudaily.ui.main.presenter.StartPresenter;

/**
 *
 * Created by Jason.fang on 2016/4/29.
 */
public class BaseOnScrollListener extends RecyclerView.OnScrollListener {
    private BaseActivity ac;
    private HomeAdapter adapter;
    private StartPresenter startPresenter;
    LoadingView loadingView;
    public BaseOnScrollListener(BaseActivity ac,StartPresenter startPresenter,HomeAdapter adapter,LoadingView loadingView){
        this.ac = ac;
        this.startPresenter = startPresenter;
        this.adapter = adapter;
        this.loadingView = loadingView;
    }
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if(newState == RecyclerView.SCROLL_STATE_IDLE){
            int lastVisibleItem = manager.findLastVisibleItemPosition();
            int totalItemCount = adapter.getItemCount();
            if (lastVisibleItem == (totalItemCount -1)) {
                loadingView.hideLoading();
                startPresenter.requestMoreNet();
                Toast.makeText(recyclerView.getContext(),"加载更多",Toast.LENGTH_LONG).show();
            }
        }
    }
    
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
        String title = adapter.getTitle(llm.findFirstVisibleItemPosition());
        ac.setTitle(title);
    }
}
