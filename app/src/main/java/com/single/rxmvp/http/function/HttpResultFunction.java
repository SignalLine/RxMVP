package com.single.rxmvp.http.function;

import com.single.rxmvp.http.exception.ExceptionEngine;
import com.single.rxmvp.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *          http结果处理函数
 */

public class HttpResultFunction<T> implements Function<Throwable,Observable<T>> {


    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        //打印具体错误
        LogUtils.e("HttpResultFunction--->" + throwable);
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
