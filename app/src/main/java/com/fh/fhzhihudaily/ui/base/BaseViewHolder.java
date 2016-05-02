package com.fh.fhzhihudaily.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Jason.fang on 2016/4/27.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    protected T data;

    public BaseViewHolder(ViewGroup parent, int resId) {
        super(createView(parent, resId));
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(T t);

    private static View createView(ViewGroup parent, int resId){
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }
}
