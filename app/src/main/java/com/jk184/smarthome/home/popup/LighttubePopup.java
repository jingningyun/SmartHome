package com.jk184.smarthome.home.popup;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk184.smarthome.R;
import com.jk184.smarthome.common.view.SwitchButton;
import com.jk184.smarthome.home.bean.Lighttube;

import butterknife.BindView;

/**
 * Created by jingning on 2019/5/30 0030 16:52
 * E-Mail Address：jingning1101@163.com
 * 灯筒控制弹窗
 */
public class LighttubePopup extends BasePopup<Lighttube> {


    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.ivLighttube)
    ImageView ivLighttube;
    @BindView(R.id.sbSwitch)
    SwitchButton sbSwitch;

    public LighttubePopup(Context context, Lighttube device) {
        super(context, device);

    }

    @Override
    protected void initView() {
        super.initView();
        sbSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                ivLighttube.setImageResource(isChecked ? R.drawable.lighttube_open : R.drawable.lighttube_close);
            }
        });
    }

    // 必须实现，这里返回您的contentView
    // 为了让库更加准确的做出适配，强烈建议使用createPopupById()进行inflate
    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_lighttube);
    }

}
