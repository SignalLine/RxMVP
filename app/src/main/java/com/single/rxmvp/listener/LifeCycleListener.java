package com.single.rxmvp.listener;

import android.os.Bundle;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description     生命周期监听
 */

public interface LifeCycleListener {

    /**
     * create
     *
     * @param savedInstanceState Bundle参数
     */
    void onCreate(Bundle savedInstanceState);

    /**
     * 开始
     */
    void onStart();

    /**
     * 重新开始
     */
    void onRestart();

    /**
     * 获取焦点
     */
    void onResume();

    /**
     *  暂停
     */
    void onPause();

    /**
     * 停止
     */
    void onStop();

    /**
     * 销毁
     */
    void onDestroy();
}
