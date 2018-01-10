package com.single.rxmvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.single.rxmvp.listener.LifeCycleListener;
import com.single.rxmvp.manager.ActivityStackManager;
import com.trello.rxlifecycle2.components.RxActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *      基类Activity
 * 备注:所有的Activity都继承自此Activity
 * 1.规范团队开发
 * 2.统一处理Activity所需配置,初始化
 */

public abstract class BaseActivity extends RxActivity implements EasyPermissions.PermissionCallbacks{

    protected Context mContext;
    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mListener != null){
            mListener.onCreate(savedInstanceState);
        }

        ActivityStackManager.getManager().push(this);
        setContentView(getContentViewId());
        mContext = this;
        mUnbinder = ButterKnife.bind(this);
        initBundleData();
        init();
    }

    /**
     * 获取视图xml文件
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化bundle数据
     */
    protected abstract void initBundleData();

    /**
     * 初始化页面数据
     */
    protected abstract void init();

    @Override
    protected void onStart() {
        super.onStart();
        if(mListener != null){
            mListener.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mListener != null) {
            mListener.onDestroy();
        }
        //移除view绑定
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        ActivityStackManager.getManager().remove(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
    }

    /**
     * 回调函数
     */
    public LifeCycleListener mListener;

    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }
}
