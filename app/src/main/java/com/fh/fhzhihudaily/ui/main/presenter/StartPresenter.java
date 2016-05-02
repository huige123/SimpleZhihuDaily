package com.fh.fhzhihudaily.ui.main.presenter;

import android.util.Log;

import com.fh.fhzhihudaily.api.retrofit.IZhihuRetrofitApi;
import com.fh.fhzhihudaily.ui.base.IView;
import com.fh.fhzhihudaily.ui.base.LoadingView;
import com.fh.fhzhihudaily.ui.main.bean.BeforeBean;
import com.fh.fhzhihudaily.ui.main.bean.InfoBean;
import com.fh.fhzhihudaily.ui.main.model.HomeListModel;
import com.fh.fhzhihudaily.utils.OkHttpUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jason.fang on 2016/4/27.
 */
public class StartPresenter {
    IZhihuRetrofitApi api;
    LoadingView loadingView;
    InfoBean infoBean;
    BeforeBean beforeBean;
    IStartView startView;
    int temp = 0;

    public StartPresenter(IStartView startView, LoadingView loadingView) {
        this.startView = startView;
        this.loadingView = loadingView;
    }

    public void onCreate() {
        api = new Retrofit.Builder()
                .client(OkHttpUtil.newOkHttpClient())
                .baseUrl("http://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IZhihuRetrofitApi.class);
    }

    public void requestNet() {
        loadingView.showLoading();
        api.getLatestStories().enqueue(new Callback<InfoBean>() {
            @Override
            public void onResponse(Call<InfoBean> call, Response<InfoBean> response) {
                loadingView.hideLoading();
                infoBean = response.body();
                requestLatest();
            }

            @Override
            public void onFailure(Call<InfoBean> call, Throwable t) {
                loadingView.hideLoading();
                loadingView.showError("加载失败");
            }
        });
    }

    public void requestMoreNet() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, temp--);
        final String s = dateFormat.format(c.getTime());
        Log.e("date", s);
        api.getBeforeStories(s).enqueue(new Callback<BeforeBean>() {
            @Override
            public void onResponse(Call<BeforeBean> call, Response<BeforeBean> response) {
                beforeBean = response.body();
                Log.e("data", beforeBean.toString());
                requestMoreList(s);
            }

            @Override
            public void onFailure(Call<BeforeBean> call, Throwable t) {

            }
        });

    }

    public void requestLatest() {
        List<HomeListModel> homeLists = new ArrayList<>();

        homeLists.add(new HomeListModel(HomeListModel.Type.VP, null, null, infoBean.topStories));

        homeLists.add(new HomeListModel(HomeListModel.Type.TITLE, null, "今日热闻", null));

        for (int i = 0; i < infoBean.stories.size(); i++) {
            homeLists.add(new HomeListModel(HomeListModel.Type.ITEM, infoBean.stories.get(i), null, null));
        }
        startView.getLatest(homeLists);
    }

    public void requestMoreList(String s) {
        List<HomeListModel> homeLists = new ArrayList<>();

        homeLists.add(new HomeListModel(HomeListModel.Type.TITLE, null, s, null));
        for (int i = 0; i < beforeBean.stories.size(); i++) {
            homeLists.add(new HomeListModel(HomeListModel.Type.ITEM, beforeBean.stories.get(i), null, null));
        }
        startView.getMoreList(homeLists);
    }


    public interface IStartView extends IView {

        void getLatest(List<HomeListModel> homeLists);

        void getMoreList(List<HomeListModel> homeListModels);
    }
}
