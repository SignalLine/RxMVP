package com.single.rxmvp.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *          吐司工具类
 */

public class ToastUtils {

    private static Toast toast;

    /**
     * 显示提示信息
     *
     * @param context  上下文
     * @param text    显示文本
     */
    public static void showToast(Context context,String text){
        if(TextUtils.isEmpty(text)){
            return;
        }

        if(toast == null){
            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }else {
            toast.setText(text);
        }

        toast.show();
    }

    /**
     * 显示提示信息
     * @param context  上下文
     * @param rId   文本资源id
     */
    public static void showToast(Context context,int rId){
        if (toast == null) {
            toast = Toast.makeText(context, rId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(rId);
        }

        toast.show();
    }

    /**
     * 显示文本信息  长时间
     * @param context  上下文
     * @param text   文本内容
     */
    public static void showLongToast(Context context,String text){
        if(TextUtils.isEmpty(text)){
            return;
        }
        if(toast == null){
            toast = Toast.makeText(context,text,Toast.LENGTH_LONG);
        }else {
            toast.setText(text);
        }

        toast.show();
    }

    /**
     * 显示提示信息(时间较长)
     * @param context  上下文
     * @param rId   文本资源id
     */
    public static void showLongToast(Context context, int rId) {
        if (toast == null) {
            toast = Toast.makeText(context, rId, Toast.LENGTH_LONG);
        } else {
            toast.setText(rId);
        }
        toast.show();

    }

}
