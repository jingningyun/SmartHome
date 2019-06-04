package com.jk184.smarthome.home.popup;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.jk184.smarthome.home.bean.Device;

import butterknife.ButterKnife;
import me.jessyan.autosize.AutoSize;
import razerdp.basepopup.BasePopupWindow;
import razerdp.blur.PopupBlurOption;

/**
 * Created by jingning on 2019/5/30 0030 16:46
 * E-Mail Address：jingning1101@163.com
 * 所有控制设备的弹窗基类，防止后续需求增加或改变
 */
public class BasePopup<T extends Device> extends BasePopupWindow {

    private T device;

    public BasePopup(Context context, T device) {
        super(context);
        ButterKnife.bind(this,getContentView());
        //模糊背景
        if (context instanceof Activity) {
            AutoSize.cancelAdapt((Activity) context);
            setBlurBackgroundEnable(true, new OnBlurOptionInitListener() {
                @Override
                public void onCreateBlurOption(PopupBlurOption option) {
                    option.setBlurPreScaleRatio(1f);
                }
            });
        }
        initView();
    }

    protected void initView() {
    }

    @Override
    public View onCreateContentView() {
        return null;
    }

    // 以下为可选代码（非必须实现）
    // 返回作用于PopupWindow的show和dismiss动画，本库提供了默认的几款动画，这里可以自由实现
    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation(true);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }
}
