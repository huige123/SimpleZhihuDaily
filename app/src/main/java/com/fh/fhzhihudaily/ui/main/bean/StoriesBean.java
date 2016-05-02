package com.fh.fhzhihudaily.ui.main.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jason.fang on 2016/4/30.
 */
public class StoriesBean {
    @Override
    public String toString() {
        return "StoriesBean{" +
                "type=" + type +
                ", id=" + id +
                ", gaPrefix='" + gaPrefix + '\'' +
                ", title='" + title + '\'' +
                ", images=" + images +
                '}';
    }

    @SerializedName("type")
    public int type;
    @SerializedName("id")
    public int id;
    @SerializedName("ga_prefix")
    public String gaPrefix;
    @SerializedName("title")
    public String title;
    @SerializedName("images")
    public List<String> images;
}
