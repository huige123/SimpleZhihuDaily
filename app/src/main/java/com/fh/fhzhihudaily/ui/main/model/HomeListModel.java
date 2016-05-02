package com.fh.fhzhihudaily.ui.main.model;

import com.fh.fhzhihudaily.ui.main.bean.BeforeBean;
import com.fh.fhzhihudaily.ui.main.bean.InfoBean;
import com.fh.fhzhihudaily.ui.main.bean.StoriesBean;

import java.util.List;

/**
 * Created by Jason.fang on 2016/4/28.
 */
public class HomeListModel {
    public Type type;
    public StoriesBean item;
    public String date;
    public List<InfoBean.TopStoriesBean> top;

    public HomeListModel(Type type, StoriesBean item, String date, List<InfoBean.TopStoriesBean> top) {
        this.type = type;
        this.item = item;
        this.date = date;
        this.top = top;
    }

    public enum Type {
        VP, ITEM, TITLE
    }
}
