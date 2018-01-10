package com.single.rxmvp.http.api;

import com.single.rxmvp.http.retrofit.HttpResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 */

public interface UserApi {

    /**
     * 用户登录
     * @param request
     * @return
     */
    @GET("user/login")
    Observable<HttpResponse> login(@QueryMap Map<String,Object> request);

}
