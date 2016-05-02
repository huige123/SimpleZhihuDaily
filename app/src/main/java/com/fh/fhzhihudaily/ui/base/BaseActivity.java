package com.fh.fhzhihudaily.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.fh.fhzhihudaily.utils.ToastUtil;
import com.fh.fhzhihudaily.widget.LoadingDialogFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * BaseActivity <br/>
 * Created by Jason.fang on 2016-04-25.
 */
public class BaseActivity extends AppCompatActivity implements LoadingView {
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            EventBus.getDefault().register(this);
        }catch (Exception e){
            Log.w(getClass().getSimpleName(), "activity has no method to subscribe", e);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getText(titleId));
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        if (dialogFragment != null) {
            dialogFragment.dismissAllowingStateLoss();
            dialogFragment = null;
        }
        dialogFragment = LoadingDialogFragment.newInstance().show(getSupportFragmentManager());
    }

    @Override
    public void hideLoading() {
        if (dialogFragment != null) {
            dialogFragment.dismissAllowingStateLoss();
            dialogFragment = null;
        }
    }

    @Override
    public void showError(CharSequence msg) {
        ToastUtil.showToast(this, msg);
    }
}
