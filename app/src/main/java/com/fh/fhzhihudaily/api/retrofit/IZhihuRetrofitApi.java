package com.fh.fhzhihudaily.api.retrofit;

import com.fh.fhzhihudaily.ui.main.bean.BeforeBean;
import com.fh.fhzhihudaily.ui.main.bean.DetailBean;
import com.fh.fhzhihudaily.ui.main.bean.InfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * IZhihuRetrofitApi <br/>
 * Created by Jason.fang on 2016-04-25.
 */
public interface IZhihuRetrofitApi {

    @GET("api/4/stories/latest")
    Call<InfoBean> getLatestStories();

    @GET("api/4/stories/before/{date}")
    Call<BeforeBean> getBeforeStories(@Path("date") String date);

    @GET("api/4/story/{id}")
    Call<DetailBean> getStory(@Path("id") String id);
}
