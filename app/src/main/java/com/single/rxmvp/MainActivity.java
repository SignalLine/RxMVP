package com.single.rxmvp;

import android.content.Intent;
import android.view.View;

import com.single.rxmvp.base.BaseActivity;
import com.single.rxmvp.view.activity.LoginActivity;
import com.single.rxmvp.view.activity.PhoneAddressActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.login,R.id.phone_address})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()) {
            case R.id.login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.phone_address:
                intent = new Intent(this, PhoneAddressActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
