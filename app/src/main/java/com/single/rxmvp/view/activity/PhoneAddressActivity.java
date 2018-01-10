package com.single.rxmvp.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.single.rxmvp.R;
import com.single.rxmvp.base.BaseActivity;
import com.single.rxmvp.model.bean.AddressBean;
import com.single.rxmvp.presenter.PhoneAddressPresenter;
import com.single.rxmvp.utils.ToastUtils;
import com.single.rxmvp.view.iface.IPhoneAddressView;
import com.single.rxmvp.widget.RLoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class PhoneAddressActivity extends BaseActivity implements IPhoneAddressView{


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_operator)
    TextView tvOperator;

    private PhoneAddressPresenter mPhonePst = new PhoneAddressPresenter(this, this);
    private RLoadingDialog mLoadingDialog;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_phone_address;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void init() {
        mLoadingDialog = new RLoadingDialog(this, true);
    }

    @OnClick({R.id.submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                attemptSubmit();
                break;
            default:
                break;
        }
    }

    /**
     * 尝试提交
     */
    private void attemptSubmit() {
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(mContext, getString(R.string.hint_phone), Toast.LENGTH_SHORT).show();
            return;
        }

        mPhonePst.getInfo(phone);

    }

    @Override
    public void showResult(AddressBean bean) {
        if (bean == null) {
            return;
        }
        tvPhone.setText(bean.getMobileNumber());
        tvCity.setText(bean.getCity());
        tvProvince.setText(bean.getProvince());
        tvOperator.setText(bean.getOperator());
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
