package com.fh.fhzhihudaily.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtil <br/>
 * Created by Jason.fang on 2016-04-25.
 */
public class ToastUtil {

    public static void showToast(Context context, CharSequence msg) {
        showToast(context, msg, false);
    }

    public static void showToast(Context context, CharSequence msg, boolean isLong) {
        Toast.makeText(context, msg, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
