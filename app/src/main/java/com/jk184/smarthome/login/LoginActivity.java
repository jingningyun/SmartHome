package com.jk184.smarthome.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jk184.smarthome.R;
import com.jk184.smarthome.app.BaseActivity;
import com.jk184.smarthome.common.constants.Constants;
import com.jk184.smarthome.common.uilts.StatusBarUtil;
import com.jk184.smarthome.home.HomeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPwd)
    EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.StatusBarDarkMode(this);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @OnClick({R.id.ivScan, R.id.tvLogin, R.id.tvForget, R.id.tvRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivScan:
                break;
            case R.id.tvLogin:
                openActivity(HomeActivity.class);
                break;
            case R.id.tvForget:
                Bundle forget = new Bundle();
                forget.putInt(Constants.BUNDLE_KEY_DATA, RegisterActivity.BUNDLE_VALUE_FORGET);
                openActivity(RegisterActivity.class, forget);
                break;
            case R.id.tvRegister:
                Bundle register = new Bundle();
                register.putInt(Constants.BUNDLE_KEY_DATA, RegisterActivity.BUNDLE_VALUE_REGISTER);
                openActivity(RegisterActivity.class, register);
                break;
        }
    }
}
