package com.goular.rx.rxuse.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.R.attr.fragment;

/**
 * Created by zhaoj on 2017/4/9.
 */

public class AutoTextFragment extends Fragment {

    private static AutoTextFragment fragment;

    public AutoTextFragment() {
        super();
    }

    public static AutoTextFragment getInstance() {
        if (fragment == null)
            fragment = new AutoTextFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
