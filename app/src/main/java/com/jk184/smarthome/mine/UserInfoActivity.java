package com.jk184.smarthome.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jk184.smarthome.R;
import com.jk184.smarthome.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jingning on 2019/5/27 0027 10:08
 * E-Mail Address：jingning1101@163.com
 */
public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.vFakeStatus)
    View vFakeStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        setFakeStatus(vFakeStatus);
    }

    @OnClick({R.id.llUser, R.id.llPwd, R.id.tvSave, R.id.ivBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llUser:
                openActivity(EditPhoneActivity.class);
                break;
            case R.id.llPwd:
                openActivity(EditPwdActivity.class);
                break;
            case R.id.tvSave:
                break;
            case R.id.ivBack:
                finish();
                break;
        }
    }
}
