package com.fh.fhzhihudaily.ui.main.presenter;

import com.fh.fhzhihudaily.api.retrofit.IZhihuRetrofitApi;
import com.fh.fhzhihudaily.ui.base.LoadingView;
import com.fh.fhzhihudaily.ui.main.bean.DetailBean;
import com.fh.fhzhihudaily.utils.OkHttpUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jason.fang on 2016/4/30.
 */
public class DetailPresenter {
    private IZhihuRetrofitApi api;
    private IDetailView detailView;
    private LoadingView loadingView;

    public DetailPresenter(IDetailView detailView, LoadingView loadingView) {
        this.detailView = detailView;
        this.loadingView = loadingView;

    }

    public void onCreated() {
        api = new Retrofit.Builder()
                .client(OkHttpUtil.newOkHttpClient())
                .baseUrl("http://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IZhihuRetrofitApi.class);
    }

    public void requestDetailNet(String id) {
        loadingView.showLoading();
        api.getStory(id).enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                loadingView.hideLoading();
                detailView.getDetailView(response.body());
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {

            }
        });
    }

    public interface IDetailView {
        void getDetailView(DetailBean detailBean);
    }
}
