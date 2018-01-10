package com.single.rxmvp.view.iface;

import com.single.rxmvp.base.IBaseView;
import com.single.rxmvp.model.bean.AddressBean;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 */

public interface IPhoneAddressView extends IBaseView {

    /**
     * 显示结果
     * @param bean
     */
    void showResult(AddressBean bean);

}
