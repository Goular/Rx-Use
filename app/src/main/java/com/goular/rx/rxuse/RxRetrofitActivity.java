package com.goular.rx.rxuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.goular.rx.R;
import com.orhanobut.logger.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RxRetrofitActivity extends AppCompatActivity {
    public static final String API_URL = "https://www.goular.com/";
    private Retrofit retrofit;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        //初始化Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //发送请求，返回数据的Observable
        subscription = retrofit.create(GetGithub.class)
                .itemDatas()
                .subscribeOn(Schedulers.io())
                .flatMap(strings -> Observable.from(strings))
                .subscribe(s -> {
                    Logger.d(s);
                });
    }

    //定义请求接口
    public interface GetGithub {
        @GET("index.php")
        Observable<List<String>> itemDatas();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅~
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }
}
