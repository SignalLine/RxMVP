package com.single.rxmvp.base;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *      BaseView
 */

public interface IBaseView {
    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 关闭加载
     */
    void closeLoading();

    /**
     * 显示吐司
     * @param msg 显示字符串内容
     */
    void showToast(String msg);
}
