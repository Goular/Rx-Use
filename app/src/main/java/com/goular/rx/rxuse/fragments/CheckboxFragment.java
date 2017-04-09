package com.goular.rx.rxuse.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.goular.rx.R;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

/**
 * Created by zhaoj on 2017/4/9.
 */

public class CheckboxFragment extends Fragment {

    private static CheckboxFragment fragment;
    private CheckBox signCB;
    private Button signBtn;
    private View rootView;

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
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_checkbox, container, false);
            signCB = (CheckBox) rootView.findViewById(R.id.sign_checkbox);
            signBtn = (Button) rootView.findViewById(R.id.sign_btn);
        }
        //初始化监听器
        initListener();
        return rootView;
    }

    private void initListener() {
        //选中事件监听
        RxCompoundButton.checkedChanges(signCB).subscribe(bool -> {
            signBtn.setEnabled(bool);
            signBtn.setBackgroundResource(bool ? R.color.colorAccent : R.color.gray);
        });
    }
}
