package com.jk184.smarthome.home.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
public class DeviceModelListAdapter extends BaseQuickAdapter<Integer, DeviceModelListAdapter.ViewHolder> {

    public DeviceModelListAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(DeviceModelListAdapter.ViewHolder helper, Integer item) {
        switch (item) {
            case DeviceConstants.DMODEL_LEAVE:
                helper.cbModel.setButtonDrawable(R.drawable.dr_dmodel_leave_s);
                helper.tvName.setText("离开");
                break;
            case DeviceConstants.DMODEL_VACATION:
                helper.cbModel.setButtonDrawable(R.drawable.dr_dmodel_vacation_s);
                helper.tvName.setText("假期");
                break;
            case DeviceConstants.DMODEL_NIGHT:
                helper.cbModel.setButtonDrawable(R.drawable.dr_dmodel_night_s);
                helper.tvName.setText("晚上");
                break;
            case DeviceConstants.DMODEL_GUEST:
                helper.cbModel.setButtonDrawable(R.drawable.dr_dmodel_guest_s);
                helper.tvName.setText("有客");
                break;
            case DeviceConstants.DMODEL_DAYTIME:
                helper.cbModel.setButtonDrawable(R.drawable.dr_dmodel_daytime_s);
                helper.tvName.setText("白天");
                break;
            default:
                break;
        }
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.cbModel)
        CheckBox cbModel;
        @BindView(R.id.tvName)
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
