package com.single.rxmvp.view.iface;

import com.single.rxmvp.base.IBaseView;
import com.single.rxmvp.model.bean.UserBean;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 */

public interface ILoginView extends IBaseView {

    /**
     * 显示结果
     * @param bean
     */
    void showResult(UserBean bean);

}
