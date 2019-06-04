package com.jk184.smarthome.home.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jk184.smarthome.R;
import com.jk184.smarthome.common.constants.DeviceConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jingning on 2019/5/28 0028 10:35
 * E-Mail Address：jingning1101@163.com
 */
public class DeviceTypeListAdapter extends BaseQuickAdapter<Integer, DeviceTypeListAdapter.ViewHolder> {

    public DeviceTypeListAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(DeviceTypeListAdapter.ViewHolder helper, Integer item) {

        switch (item) {
            case DeviceConstants.DTYPE_ALL:
                helper.cbType.setButtonDrawable(R.drawable.dr_dtype_all_s);
                break;
            case DeviceConstants.DTYPE_SCENES:
                helper.cbType.setButtonDrawable(R.drawable.dr_dtype_scenes_s);
                break;
            case DeviceConstants.DTYPE_LIGHT:
                helper.cbType.setButtonDrawable(R.drawable.dr_dtype_light_s);
                break;
            case DeviceConstants.DTYPE_CURTAIN:
                helper.cbType.setButtonDrawable(R.drawable.dr_dtype_curtain_s);
                break;
            case DeviceConstants.DTYPE_AIRCONDITIONING:
                helper.cbType.setButtonDrawable(R.drawable.dr_dtype_airconditioning_s);
                break;
            case DeviceConstants.DTYPE_MUSIC:
                helper.cbType.setButtonDrawable(R.drawable.dr_dtype_music_s);
                break;
            case DeviceConstants.DTYPE_SAFE:
                helper.cbType.setButtonDrawable(R.drawable.dr_dtype_safe_s);
                break;
            case DeviceConstants.DTYPE_COMPUTER:
                helper.cbType.setButtonDrawable(R.drawable.dr_dtype_computer_s);
                break;
            default:
                break;
        }
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.cbType)
        CheckBox cbType;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
