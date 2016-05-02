package com.fh.fhzhihudaily.ui.main.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jason.fang on 2016/4/30.
 */
public class DetailBean {

    @SerializedName("body")
    public String body;
    @SerializedName("image_source")
    public String imageSource;
    @SerializedName("title")
    public String title;
    @SerializedName("image")
    public String image;
    @SerializedName("share_url")
    public String shareUrl;
    @SerializedName("ga_prefix")
    public String gaPrefix;
    @SerializedName("type")
    public int type;
    @SerializedName("id")
    public int id;
    @SerializedName("js")
    public List<String> js;
    @SerializedName("images")
    public List<String> images;
    @SerializedName("css")
    public List<String> css;
}
