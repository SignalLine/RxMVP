package com.single.rxmvp.http.observer;

import android.text.TextUtils;

import com.single.rxmvp.http.exception.ApiException;
import com.single.rxmvp.http.exception.ExceptionEngine;
import com.single.rxmvp.http.retrofit.HttpRequestListener;
import com.single.rxmvp.http.retrofit.RxActionManagerImpl;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *      适用Retrofit网络请求Observer(监听者)
 *
 *      备注:
 *          1.重写onSubscribe，添加请求标识
 *          2.重写onError，封装错误/异常处理，移除请求
 *          3.重写onNext，移除请求
 *          4.重写cancel，取消请求
 */

public abstract class HttpRxObserver<T> implements Observer<T>,HttpRequestListener {
    //请求标识
    private String mTag;

    public HttpRxObserver(){

    }

    public HttpRxObserver(String tag){
        this.mTag = tag;
    }

    @Override
    public void onError(@NonNull Throwable e) {
        RxActionManagerImpl.getInstance().remove(mTag);
        if(e instanceof ApiException){
            onError((ApiException)e);
        }else{
            onError(new ApiException(e, ExceptionEngine.UN_KNOWN_ERROR));
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onNext(@NonNull T t) {
        if(!TextUtils.isEmpty(mTag)){
            RxActionManagerImpl.getInstance().remove(mTag);
        }

        onSuccess(t);
    }

    @Override
    public void cancel() {
        if(!TextUtils.isEmpty(mTag)){
            RxActionManagerImpl.getInstance().cancel(mTag);
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if(!TextUtils.isEmpty(mTag)){
            RxActionManagerImpl.getInstance().add(mTag,d);
        }

        onStart(d);
    }

    /**
     * 是否已经处理
     *
     * @return
     */
    public boolean isDisposed(){
        if(TextUtils.isEmpty(mTag)){
            return true;
        }
        return RxActionManagerImpl.getInstance().isDisposed(mTag);
    }

    /**
     * 开始监听
     *
     * @param d
     */
    protected abstract void onStart(Disposable d);

    /**
     * 成功回调
     *
     * @param response
     */
    protected abstract void onSuccess(T response);

    /**
     * 错误/异常回调
     *
     * @param e
     */
    protected abstract void onError(ApiException e);
}
