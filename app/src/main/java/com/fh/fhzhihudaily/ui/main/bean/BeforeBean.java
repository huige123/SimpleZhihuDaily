package com.fh.fhzhihudaily.ui.main.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jason.fang on 2016/4/28.
 */
public class BeforeBean {
    @Override
    public String toString() {
        return "BeforeBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}';
    }

    @SerializedName("date")
    public String date;

    @SerializedName("stories")
    public List<StoriesBean> stories;

}
