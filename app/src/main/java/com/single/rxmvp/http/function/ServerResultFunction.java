package com.single.rxmvp.http.function;

import com.google.gson.Gson;
import com.single.rxmvp.http.exception.ServerException;
import com.single.rxmvp.http.retrofit.HttpResponse;
import com.single.rxmvp.utils.LogUtils;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *
 *          服务器结果处理函数
 */

public class ServerResultFunction implements Function<HttpResponse,Object> {

    @Override
    public Object apply(@NonNull HttpResponse httpResponse) throws Exception {
        //打印服务器回传结果
        LogUtils.e(httpResponse.toString());

        if(!httpResponse.isSuccess()){
            throw new ServerException(httpResponse.getCode(),httpResponse.getMsg());
        }
        return new Gson().toJson(httpResponse.getResult());
    }
}
