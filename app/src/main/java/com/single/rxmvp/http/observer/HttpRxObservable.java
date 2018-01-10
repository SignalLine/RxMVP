package com.single.rxmvp.http.observer;


import com.single.rxmvp.http.function.HttpResultFunction;
import com.single.rxmvp.http.function.ServerResultFunction;
import com.single.rxmvp.http.retrofit.HttpResponse;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *          适用于Retrofit网络请求Observable（被监听者）
 */

public class HttpRxObservable {

    /**
     * 获取被监听者
     * 网络请求Observable构建
     * data网络请求参数
     *
     *  补充说明----无管理声明周期，容易导致内存溢出
     * @param apiObservable
     * @return
     */
    public static Observable getObservable(Observable<HttpResponse> apiObservable){
        //showLog(request);
        Observable observable = apiObservable
                .map(new ServerResultFunction())
                .onErrorResumeNext(new HttpResultFunction<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return observable;
    }

    /**
     *  获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider自动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity.../RxFragment...
     *
     * @param apiObservable
     * @param lifecycle
     * @return
     */
    public static Observable getObservable(Observable<HttpResponse> apiObservable, LifecycleProvider lifecycle){
        //showLog(request);
        Observable observable;

        if(lifecycle != null){
            //随声明周期自动管理 eg:onCreate(start)->onStop(end)
            observable = apiObservable
                    .map(new ServerResultFunction())
                    ////需要在这里添加
                    .compose(lifecycle.bindToLifecycle())
                    .onErrorResumeNext(new HttpResultFunction<>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }else {
            observable = getObservable(apiObservable);
        }

        return observable;
    }

    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入LifecycleProvider<ActivityEvent>手动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity,RxAppCompatActivity,RxFragmentActivity
     *
     * @param apiObservable
     * @param lifecycle
     * @param event
     * @return
     */
    public static Observable getObservable(Observable<HttpResponse> apiObservable,
                                           LifecycleProvider<ActivityEvent> lifecycle,
                                           ActivityEvent event){
        // showLog(request);
        Observable observable;
        if (lifecycle != null) {
            //手动管理移除监听生命周期.eg:ActivityEvent.STOP
            observable = apiObservable
                    .map(new ServerResultFunction())
                    //需要在这个位置添加
                    .compose(lifecycle.bindUntilEvent(event))
                    .onErrorResumeNext(new HttpResultFunction<>())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            observable = getObservable(apiObservable);
        }
        return observable;
    }

}
