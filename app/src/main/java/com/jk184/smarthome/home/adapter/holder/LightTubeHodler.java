package com.jk184.smarthome.home.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk184.smarthome.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jingning on 2019/5/28 0028 17:08
 * E-Mail Address：jingning1101@163.com
 * 灯筒
 */
public class LightTubeHodler extends BaseDeviceHodler {

    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.tvName)
    public
    TextView tvName;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.cbSwitch)
    public
    CheckBox cbSwitch;

    private Context context;

    public LightTubeHodler(Context context, @NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
