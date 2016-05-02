package com.fh.fhzhihudaily.ui.main.presenter;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fh.fhzhihudaily.R;
import com.fh.fhzhihudaily.ui.base.BaseViewHolder;
import com.fh.fhzhihudaily.ui.main.model.HomeListModel;
import com.fh.fhzhihudaily.ui.main.view.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Jason.fang on 2016/4/27.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeListModel> homeLists;

    @Override
    public int getItemViewType(int position) {
        return homeLists.get(position).type.ordinal();
    }

    public HomeAdapter(List<HomeListModel> homeLists) {
        this.homeLists = homeLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (HomeListModel.Type.values()[viewType]) {
            case VP:
                return new VPViewHolder(parent);
            case ITEM:
                return new MyViewHolder(parent);
            case TITLE:
                return new DateViewHolder(parent);
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder<HomeListModel>) holder).bind(homeLists.get(position));
    }

    public void add(HomeListModel model) {
        homeLists.add(model);
        notifyDataSetChanged();
    }

    public void addAll(List<HomeListModel> more) {
        homeLists.addAll(more);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return homeLists.size();
    }

    public void clear() {
        homeLists.clear();
        notifyDataSetChanged();
    }

    public String getTitle(int position) {
        while (position >= 0 && homeLists.get(position).type != HomeListModel.Type.TITLE) {
            position--;
        }
        if (position < 0) return "首页";
        return homeLists.get(position).date;
    }


    static class MyViewHolder extends BaseViewHolder<HomeListModel> {
        @Bind(R.id.text_list)
        TextView tv;
        @Bind(R.id.image_list)
        ImageView iv;


        public MyViewHolder(ViewGroup parent) {
            super(parent, R.layout.homepage_list);
        }

        @Override
        public void bind(HomeListModel homeList) {
            this.data = homeList;
            Picasso.with(iv.getContext()).load(homeList.item.images.get(0)).into(iv);
            tv.setText(homeList.item.title);
        }

        @OnClick(R.id.container)
        public void jumpToDetail(View v) {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("id", String.valueOf(data.item.id));
            v.getContext().startActivity(intent);
        }
    }

    static class DateViewHolder extends BaseViewHolder<HomeListModel> {
        @Bind(R.id.text_date)
        TextView tv;

        public DateViewHolder(ViewGroup parent) {
            super(parent, R.layout.homepage_date);
        }

        @Override
        public void bind(HomeListModel data) {
            this.data = data;
            tv.setText(data.date);
        }
    }

    static class VPViewHolder extends BaseViewHolder<HomeListModel> {
        @Bind(R.id.vp)
        ViewPager vp;

        public VPViewHolder(ViewGroup parent) {
            super(parent, R.layout.home_page_vp);
        }

        @Override
        public void bind(HomeListModel homeListModel) {
            this.data = homeListModel;
            AppCompatActivity ac = (AppCompatActivity) itemView.getContext();
            vp.setAdapter(new FraPagerAdapter(ac.getSupportFragmentManager(), data.top));
        }
    }
}
