package com.fh.fhzhihudaily.utils;

import com.fh.fhzhihudaily.api.net.NetConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttpUtil <br/>
 * Created by Jason.fang on 2016-04-25.
 */
public class OkHttpUtil {

    public static OkHttpClient newOkHttpClient() {
        return new OkHttpClient.Builder()
                .writeTimeout(NetConstants.TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(NetConstants.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(NetConstants.TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
}
