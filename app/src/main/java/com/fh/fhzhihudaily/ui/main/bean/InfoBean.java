package com.fh.fhzhihudaily.ui.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jason.fang on 2016/4/27.
 */
public class InfoBean {
    @Override
    public String toString() {
        return "InfoBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", topStories=" + topStories +
                '}';
    }

    @SerializedName("date")
    public String date;

    @SerializedName("stories")
    public List<StoriesBean> stories;

    @SerializedName("top_stories")
    public List<TopStoriesBean> topStories;

    public static class TopStoriesBean implements Parcelable{
        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "image='" + image + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", gaPrefix='" + gaPrefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        @SerializedName("image")
        public String image;
        @SerializedName("type")
        public int type;
        @SerializedName("id")
        public int id;
        @SerializedName("ga_prefix")
        public String gaPrefix;
        @SerializedName("title")
        public String title;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.image);
            dest.writeInt(this.type);
            dest.writeInt(this.id);
            dest.writeString(this.gaPrefix);
            dest.writeString(this.title);
        }

        public TopStoriesBean() {
        }

        protected TopStoriesBean(Parcel in) {
            this.image = in.readString();
            this.type = in.readInt();
            this.id = in.readInt();
            this.gaPrefix = in.readString();
            this.title = in.readString();
        }

        public static final Creator<TopStoriesBean> CREATOR = new Creator<TopStoriesBean>() {
            @Override
            public TopStoriesBean createFromParcel(Parcel source) {
                return new TopStoriesBean(source);
            }

            @Override
            public TopStoriesBean[] newArray(int size) {
                return new TopStoriesBean[size];
            }
        };
    }
}
