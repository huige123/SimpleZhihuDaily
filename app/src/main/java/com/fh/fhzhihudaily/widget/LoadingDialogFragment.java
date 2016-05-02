package com.fh.fhzhihudaily.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fh.fhzhihudaily.R;


public class LoadingDialogFragment extends DialogFragment {

    static final String KEY_CANCELABLE = "cancelable";

    static final String KEY_MSG = "msg";

    public static LoadingDialogFragment newInstance() {
        return newInstance(null);
    }

    public static LoadingDialogFragment newInstance(boolean cancelable) {
        return newInstance(cancelable, "");
    }

    public static LoadingDialogFragment newInstance(String msg) {
        return newInstance(false, msg);
    }

    public static LoadingDialogFragment newInstance(boolean cancelable, String msg) {
        LoadingDialogFragment f = new LoadingDialogFragment();
        Bundle args = new Bundle();
        args.putString(KEY_MSG, msg);
        args.putBoolean(KEY_CANCELABLE, cancelable);
        f.setArguments(args);
        f.setCancelable(cancelable);
        return f;
    }

    private OnCancelListener onCancelListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        boolean cancelable = getArguments().getBoolean(KEY_CANCELABLE, false);
        String msg = getArguments().getString(KEY_MSG);
        View root = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        TextView tv = (TextView) root.findViewById(R.id.tv);
        if (!TextUtils.isEmpty(msg)) {
            tv.setText(msg);
        }
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Holo_Dialog);
        dialog.setContentView(root);
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelable);
        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialog);
        }
    }

    public LoadingDialogFragment show(FragmentManager fragmentManager) {
        super.show(fragmentManager, null);
        return this;
    }

    public LoadingDialogFragment onCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }
}