package com.goular.rx.rxgank.retrofitUtils;

import com.goular.rx.rxgank.model.GirlData;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 获取接口的内容
 */
public interface GirlApi {
    @GET("data/福利/10/{page}")
    Observable<Result<GirlData>> fetchPrettyGirl(@Path("page") int page);
}
