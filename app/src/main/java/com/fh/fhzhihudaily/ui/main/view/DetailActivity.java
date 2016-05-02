package com.fh.fhzhihudaily.ui.main.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.fh.fhzhihudaily.R;
import com.fh.fhzhihudaily.ui.base.BaseActivity;
import com.fh.fhzhihudaily.ui.main.bean.DetailBean;
import com.fh.fhzhihudaily.ui.main.presenter.DetailPresenter;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import butterknife.Bind;

/**
 * Created by Jason.fang on 2016/4/30.
 */
public class DetailActivity extends BaseActivity implements DetailPresenter.IDetailView {
    @Bind(R.id.detail_image)
    ImageView detailImage;

    @Bind(R.id.detail_text)
    TextView detailText;

    @Bind(R.id.detail_title)
    TextView detailTitle;

    DetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        String id = getIntent().getStringExtra("id");
        Log.e("errorr", id);
        presenter = new DetailPresenter(this, this);
        presenter.onCreated();
        presenter.requestDetailNet(id);
    }

    @Override
    public void getDetailView(final DetailBean detailBean) {
        Picasso.with(this).load(detailBean.image).into(detailImage);

        new Thread(new Runnable() {
            int widthPixels = getResources().getDisplayMetrics().widthPixels;

            @Override
            public void run() {
                final CharSequence s = Html.fromHtml(detailBean.body, new Html.ImageGetter() {
                    Drawable drawable = null;
                    boolean first = true;

                    @Override
                    public Drawable getDrawable(String source) {
                        try {
                            URL url = new URL(source);
                            drawable = Drawable.createFromStream(url.openStream(), "");
                            int height = widthPixels * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth();
                            if (!first) {
                                drawable.setBounds(0, 0, widthPixels, height);
                            } else {
                                first = false;
                                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return drawable;
                    }
                }, null);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        detailText.setText(s);
                    }
                });
            }
        }).start();

        detailTitle.setText(detailBean.title);
    }
}
