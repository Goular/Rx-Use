package com.goular.rx.rxuse.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhaoj on 2017/4/9.
 */

public class CheckboxFragment extends Fragment {

    private static CheckboxFragment fragment;

    public CheckboxFragment() {
        super();
    }

    public static CheckboxFragment getInstance() {
        if (fragment == null)
            fragment = new CheckboxFragment();
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
