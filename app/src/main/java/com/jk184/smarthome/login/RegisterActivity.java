package com.jk184.smarthome.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk184.smarthome.R;
import com.jk184.smarthome.app.BaseActivity;
import com.jk184.smarthome.common.constants.Constants;
import com.jk184.smarthome.common.uilts.StatusBarUtil;
import com.jk184.smarthome.common.view.CountDownView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jingning on 2019/5/22 0022 14:49
 * E-Mail Address：jingning1101@163.com
 */
public class RegisterActivity extends BaseActivity {

    public static final int BUNDLE_VALUE_REGISTER = 1;
    public static final int BUNDLE_VALUE_FORGET = 2;

    private final int COUNT_DOWN_SECOUND = 60;//倒计时30s

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etValidCode)
    EditText etValidCode;
    @BindView(R.id.cdvValidCode)
    CountDownView cdvValidCode;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etConfirmPwd)
    EditText etConfirmPwd;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.ivAgree)
    ImageView ivAgree;
    @BindView(R.id.tvAgreement)
    TextView tvAgreement;

    private int type;//1是注册，2是忘记密码

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.StatusBarDarkMode(this);
        setContentView(R.layout.activity_register);
        StatusBarUtil.setTransparent(this);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        if(intent != null){
            type = intent.getIntExtra(Constants.BUNDLE_KEY_DATA, BUNDLE_VALUE_REGISTER);
        }
        if(type == BUNDLE_VALUE_FORGET){
            tvTitle.setText("找回密码");
            etPwd.setHint("输入新密码");
            tvRegister.setText("确定");
            ivAgree.setVisibility(View.GONE);
            tvAgreement.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.ivScan, R.id.cdvValidCode, R.id.tvRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivScan:
                break;
            case R.id.cdvValidCode:
                cdvValidCode.setEnabled(false);
                startCountDown();
                break;
            case R.id.tvRegister:
                finish();
                break;
        }
    }


    /**
     * 开始倒计时
     */
    public void startCountDown() {
        cdvValidCode.startCountDown(COUNT_DOWN_SECOUND, mCallback);
    }

    CountDownView.CountDownCallBack mCallback = new CountDownView.CountDownCallBack() {
        @Override
        public void onCountDown(int second) {
            cdvValidCode.setText(String.format("剩余%s秒", String.valueOf(second)));
        }

        @Override
        public void onCountComplate() {
            cdvValidCode.setText("获取验证码");
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cdvValidCode.stopContDown();
    }
}
