package com.goular.rx.rxuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.goular.rx.R;
import com.orhanobut.logger.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.functions.Action1;

/**
 * 普通的Retrofit2的使用方法，不含RxJava
 */
public class RetrofitActivity extends AppCompatActivity {
    public static final String API_URL = "https://www.goular.com/";

    //https://raw.githubusercontent.com/zhangkekekeke/RxJavaObserver/master/jsondata
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<String>> call = retrofit.create(GitHub.class).dataXiaoHua();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Logger.d(response.body());
                Observable.from(response.body())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String itemData) {
                                Logger.d("("+"RxJava_R", itemData + ")");
                            }
                        });

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("RxJava_F", t.getMessage());
            }
        });

        //取消请求
//        call.cancel();

    }


    public interface GitHub {
        @GET("/{owner}/{txt}/master/jsondata")
        Call<List<String>> itemDatas(
                @Path("owner") String owner,
                @Path("txt") String repo);

        @GET("index.php")
        Call<List<String>> dataXiaoHua();
    }
}
