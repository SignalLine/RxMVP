package com.single.rxmvp.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.single.rxmvp.R;
import com.single.rxmvp.base.BaseActivity;
import com.single.rxmvp.model.bean.UserBean;
import com.single.rxmvp.presenter.LoginPresenter;
import com.single.rxmvp.utils.ToastUtils;
import com.single.rxmvp.view.iface.ILoginView;
import com.single.rxmvp.widget.RLoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;

    private LoginPresenter mLoginPresenter = new LoginPresenter(this, this);

    private RLoadingDialog mLoadingDialog;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void init() {
        mLoadingDialog = new RLoadingDialog(this, true);
    }

    @OnClick({R.id.login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    return;
                }

                mLoginPresenter.login(userName, password);
                break;
            default:
                break;
        }
    }

    @Override
    public void showResult(UserBean bean) {
        if(bean == null){
            return;
        }
        showToast(bean.getUid());
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void closeLoading() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContext, msg);
    }

}
