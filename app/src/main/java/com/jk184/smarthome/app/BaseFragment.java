package com.jk184.smarthome.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jk184.smarthome.common.uilts.StatusBarUtil;
import com.jk184.smarthome.common.uilts.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;
import me.jessyan.autosize.AutoSize;

/**
 * Created by jingning on 2019/5/24 0024 16:09
 * E-Mail Address：jingning1101@163.com
 */
public class BaseFragment extends Fragment {

    private Unbinder mUnBinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //修复ButterKnife框架在分model下开发无法注入的bug
        //https://github.com/JakeWharton/butterknife/issues/1127
        mUnBinder = ButterKnife.bind(this, view);
        initView(view);
    }

    protected void initView(View view) {

    }


    public void setFakeStatus(View fakeStatus, Activity activity) {
        AutoSize.cancelAdapt(activity);//修改系统高度，先取消适配
        fakeStatus.getLayoutParams().height = StatusBarUtil.getStatusBarHeight(activity);
    }

    protected void openActivity(Class<? extends Activity> pClass) {
        openActivity(pClass, null);
    }


    protected void openActivity(Class<? extends Activity> pClass, Bundle pBundle) {
        Intent intent = new Intent(getActivity(), pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    protected void openActivityForResult(Class<? extends Activity> pClass, int requestCode) {
        openActivityForResult(pClass, null, requestCode);
    }

    protected void openActivityForResult(Class<? extends Activity> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(getActivity(), pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void toast(String msg) {
        if (getActivity() != null) {
            AutoSize.cancelAdapt(getActivity());//就是 show() 前取消适配 AutoSize#cancelAdapt，不过在 AndroidAutoSize 中，取消适配后不需要再重新适配
            ToastUtils.showToast(getActivity().getApplicationContext(), msg);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }
}
