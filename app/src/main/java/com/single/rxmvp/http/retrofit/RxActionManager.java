package com.single.rxmvp.http.retrofit;

import io.reactivex.disposables.Disposable;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *          RxJavaAction管理接口
 */

public interface RxActionManager<T> {

    /**
     * 添加
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 取消
     *
     * @param tag
     */
    void cancel(T tag);

}
