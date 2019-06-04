package com.jk184.smarthome.home.popup;

import android.content.Context;
import android.view.View;

import com.jk184.smarthome.R;
import com.jk184.smarthome.home.bean.Spotlight;

/**
 * Created by jingning on 2019/5/30 0030 20:23
 * E-Mail Address：jingning1101@163.com
 */
public class SpotlightPopup extends BasePopup<Spotlight> {

    public SpotlightPopup(Context context, Spotlight device) {
        super(context, device);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_spotlight);
    }
}
