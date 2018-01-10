package com.single.rxmvp.presenter;

import com.google.gson.Gson;
import com.single.rxmvp.base.BasePresenter;
import com.single.rxmvp.http.api.ApiUtils;
import com.single.rxmvp.http.exception.ApiException;
import com.single.rxmvp.http.observer.HttpRxObservable;
import com.single.rxmvp.http.observer.HttpRxObserver;
import com.single.rxmvp.http.retrofit.HttpRequest;
import com.single.rxmvp.model.bean.UserBean;
import com.single.rxmvp.utils.LogUtils;
import com.single.rxmvp.view.activity.LoginActivity;
import com.single.rxmvp.view.iface.ILoginView;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *          登录presenter
 */

public class LoginPresenter extends BasePresenter<ILoginView,LoginActivity> {

    private final String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter(ILoginView view,LoginActivity activity){
        super(view,activity);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    public void login(String username,String password){
        Map<String , Object> request = HttpRequest.getRequest();
        request.put("username",username);
        request.put("password",password);

        HttpRxObserver httpRxObserver = new HttpRxObserver(TAG + "getInfo") {
            @Override
            protected void onStart(Disposable d) {
                if(getView() != null){
                    getView().showLoading();
                }
            }

            @Override
            protected void onSuccess(Object response) {
                LogUtils.w("onSuccess response:" + response.toString());
                UserBean bean = new Gson().fromJson(response.toString(), UserBean.class);
                if (getView() != null) {
                    getView().closeLoading();
                    getView().showResult(bean);
                }
            }

            @Override
            protected void onError(ApiException e) {
                LogUtils.w("onError code:" + e.getCode() + " msg:" + e.getMsg());
                if (getView() != null) {
                    getView().closeLoading();
                    getView().showToast(e.getMsg());
                }
            }
        };

        /**
         * 切入后台移除RxJava监听
         * ActivityEvent.PAUSE(FragmentEvent.PAUSE)
         * 手动管理移除RxJava监听,如果不设置此参数默认自动管理移除RxJava监听（onCrete创建,onDestroy移除）
         */
        HttpRxObservable.getObservable(ApiUtils.getUserApi().login(request),
                                    getActivity(),
                                    ActivityEvent.PAUSE).subscribe(httpRxObserver);


        /**
         * ******此处代码为了测试取消请求,不是规范代码*****
         */
        /*try {
            Thread.sleep(50);
            //取消请求
            if (!httpRxObserver.isDisposed()) {
                httpRxObserver.cancel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}
