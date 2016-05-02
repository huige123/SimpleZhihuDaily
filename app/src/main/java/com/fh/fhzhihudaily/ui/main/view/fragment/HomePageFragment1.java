package com.fh.fhzhihudaily.ui.main.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fh.fhzhihudaily.R;
import com.fh.fhzhihudaily.api.retrofit.IZhihuRetrofitApi;
import com.fh.fhzhihudaily.ui.main.bean.DetailBean;
import com.fh.fhzhihudaily.ui.main.bean.InfoBean;
import com.fh.fhzhihudaily.ui.main.presenter.DetailPresenter;
import com.fh.fhzhihudaily.ui.main.view.DetailActivity;
import com.fh.fhzhihudaily.ui.main.view.StartActivity;
import com.fh.fhzhihudaily.utils.OkHttpUtil;
import com.fh.fhzhihudaily.utils.ToastUtil;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Jason.fang on 2016/4/27.
 */
public class HomePageFragment1 extends Fragment{


    private static final String KEY_BEAN = "key_bean";

    private InfoBean.TopStoriesBean bean;

    @Bind(R.id.text_title)
    TextView textView;

    @Bind(R.id.image1)
    ImageView view1;

    public static HomePageFragment1 newInstance(InfoBean.TopStoriesBean bean) {

        Bundle args = new Bundle();
        args.putParcelable(KEY_BEAN, bean);

        HomePageFragment1 fragment = new HomePageFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bean = getArguments().getParcelable(KEY_BEAN);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homepage_image1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);


        Picasso.with(getContext()).load(bean.image).into(view1);
        textView.setText(bean.title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("id", String.valueOf(bean.id));
                startActivity(intent);
            }
        });
    }

}
