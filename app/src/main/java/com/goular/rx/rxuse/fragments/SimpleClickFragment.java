package com.goular.rx.rxuse.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.goular.rx.R;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;

/**
 * Created by zhaoj on 2017/4/9.
 */

public class SimpleClickFragment extends Fragment {

    private static SimpleClickFragment fragment;
    @BindView(R.id.clicks_btn)
    Button clicksBtn;
    @BindView(R.id.mlistview)
    ListView mlistview;
    Unbinder unbinder;
    private View rootView;
    private List<String> mList;
    private Subscription clickSubscription;

    public SimpleClickFragment() {
        super();
    }

    public static SimpleClickFragment getInstance() {
        if (fragment == null)
            fragment = new SimpleClickFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建模拟数据
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_simple_click, null);
        }
        unbinder = ButterKnife.bind(this, rootView);
        //初始化适配器，监听器
        initAdapter();
        initListener();
        return rootView;
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("item0" + i);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int i = 0;

    //初始化监听器
    private void initListener() {
        //点击监听，添加了throttleFirst方法可以防抖动
        //throttleFirst,为一段时间内仅能为第一次行为
        RxView.clicks(clicksBtn).throttleFirst(600, TimeUnit.MILLISECONDS).subscribe(Void -> {
            //Snackbar第一个参数可以是任意的view，他会往上找，最后找到rootView
            Snackbar.make(clicksBtn, "发送了" + ++i + "个事件", Snackbar.LENGTH_SHORT).show();
        });
    }

    //初始化适配器
    private void initAdapter() {
    }
}
