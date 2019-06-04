package com.jk184.smarthome.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import com.jk184.smarthome.R;
import com.jk184.smarthome.common.uilts.StatusBarUtil;
import com.jk184.smarthome.common.uilts.ToastUtils;

import butterknife.ButterKnife;
import me.jessyan.autosize.AutoSize;

/**
 * Created by jingning on 2019/5/21 0021 21:19
 * E-Mail Address：jingning1101@163.com
 */
public class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        StatusBarUtil.StatusBarLightMode(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.main_bg), 0);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initView();
    }

    public void setFakeStatus(View fakeStatus){
        AutoSize.cancelAdapt(this);//修改系统高度，先取消适配
        fakeStatus.getLayoutParams().height = StatusBarUtil.getStatusBarHeight(this);
    }

    protected void initView() {
    }

    protected void openActivity(Class<? extends Activity> pClass) {
        openActivity(pClass, null);
    }


    protected void openActivity(Class<? extends Activity> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    protected void openActivityForResult(Class<? extends Activity> pClass, int requestCode) {
        openActivityForResult(pClass, null, requestCode);
    }

    protected void openActivityForResult(Class<? extends Activity> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void toast(String msg) {
        if (mContext != null) {
            AutoSize.cancelAdapt(this);//就是 show() 前取消适配 AutoSize#cancelAdapt，不过在 AndroidAutoSize 中，取消适配后不需要再重新适配
            ToastUtils.showToast(mContext.getApplicationContext(), msg);
        }
    }
}
