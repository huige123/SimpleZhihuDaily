package com.fh.fhzhihudaily.ui.base;

/**
 * LoadingView <br/>
 * Created by Jason.fang on 2016-04-25.
 */
public interface LoadingView {

    void showLoading();

    void hideLoading();

    void showError(CharSequence msg);
}
