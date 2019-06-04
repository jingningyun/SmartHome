package com.jk184.smarthome.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jk184.smarthome.R;
import com.jk184.smarthome.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jingning on 2019/5/27 0027 11:09
 * E-Mail Address：jingning1101@163.com
 */
public class EditPwdActivity extends BaseActivity {

    @BindView(R.id.vFakeStatus)
    View vFakeStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpwd);
        setFakeStatus(vFakeStatus);
    }
}
