package com.single.rxmvp.presenter;

import com.google.gson.Gson;
import com.single.rxmvp.base.BasePresenter;
import com.single.rxmvp.http.api.ApiUtils;
import com.single.rxmvp.http.exception.ApiException;
import com.single.rxmvp.http.observer.HttpRxObservable;
import com.single.rxmvp.http.observer.HttpRxObserver;
import com.single.rxmvp.http.retrofit.HttpRequest;
import com.single.rxmvp.model.bean.AddressBean;
import com.single.rxmvp.utils.LogUtils;
import com.single.rxmvp.view.activity.PhoneAddressActivity;
import com.single.rxmvp.view.iface.IPhoneAddressView;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 */

public class PhoneAddressPresenter extends BasePresenter<IPhoneAddressView,PhoneAddressActivity>{

    private final String TAG = PhoneAddressPresenter.class.getSimpleName();

    public PhoneAddressPresenter(IPhoneAddressView view, PhoneAddressActivity activity) {
        super(view, activity);
    }

    /**
     * 获取信息
     */
    public void getInfo(String phone) {
        //构建请求数据
        Map<String, Object> request = HttpRequest.getRequest();
        request.put("phone", phone);

        HttpRxObserver httpRxObserver = new HttpRxObserver(TAG + "getInfo") {

            @Override
            protected void onStart(Disposable d) {
                if (getView() != null) {
                    getView().showLoading();
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

            @Override
            protected void onSuccess(Object response) {
                LogUtils.w("onSuccess response:" + response.toString());
                AddressBean bean = new Gson().fromJson(response.toString(), AddressBean.class);
                if (getView() != null) {
                    getView().closeLoading();
                    getView().showResult(bean);
                }
            }
        };

        HttpRxObservable.getObservable(ApiUtils.getPhoneApi().phoneQuery(request), getActivity())
                .subscribe(httpRxObserver);

        //取消请求
        /*if(!httpRxObserver.isDisposed()){
            httpRxObserver.cancel();
        }*/

    }

}
