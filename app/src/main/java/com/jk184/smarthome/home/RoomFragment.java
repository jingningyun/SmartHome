package com.jk184.smarthome.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jk184.smarthome.R;
import com.jk184.smarthome.app.BaseFragment;
import com.jk184.smarthome.common.constants.DeviceConstants;
import com.jk184.smarthome.home.adapter.DeviceListAdapter;
import com.jk184.smarthome.home.bean.Device;
import com.jk184.smarthome.home.bean.Lighttube;
import com.jk184.smarthome.home.bean.LivingCurtain;
import com.jk184.smarthome.home.bean.Spotlight;
import com.jk184.smarthome.home.popup.LighttubePopup;
import com.jk184.smarthome.home.popup.LivingCurtainPopup;
import com.jk184.smarthome.home.popup.SpotlightPopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jingning on 2019/5/23 0023 14:43
 * E-Mail Address：jingning1101@163.com
 */
public class RoomFragment extends BaseFragment {

    @BindView(R.id.rvDevice)
    RecyclerView rvDevice;

    //该房间下的设备列表适配
    private DeviceListAdapter deviceListAdapter;
    private List<Device> devices;

    public static RoomFragment newInstance() {
        RoomFragment fragment = new RoomFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rvDevice.setLayoutManager(new GridLayoutManager(getContext(), 2));
        devices = new ArrayList<Device>();
        devices.add(new Lighttube(1));
        devices.add(new Spotlight(2));
        devices.add(new LivingCurtain(6));
        devices.add(new Device(12));
        devices.add(new Device(10));
        devices.add(new Device(3));
        devices.add(new Device(11));
        devices.add(new Device(4));
        devices.add(new Device(9));
        devices.add(new Device(8));
        devices.add(new Device(5));
        devices.add(new Device(7));
        deviceListAdapter = new DeviceListAdapter(getContext(), devices);
        rvDevice.setAdapter(deviceListAdapter);
        deviceListAdapter.setOnItemClickListener(onItemClickListener);

    }

    private DeviceListAdapter.OnItemClickListener onItemClickListener = new DeviceListAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(DeviceListAdapter adapter, View view, int position) {
            openDevicePopup(adapter.getItem(position));
        }
    };

    private void openDevicePopup(Device device){
        switch (device.getType()){
            case DeviceConstants
                    .DEVICE_LIGHTTUBE://灯筒
                new LighttubePopup(getContext(), (Lighttube) device).showPopupWindow();
                break;

            case DeviceConstants
                    .DEVICE_SPOTLIGHT://射灯
                new SpotlightPopup(getContext(), (Spotlight) device).showPopupWindow();
                break;


            case DeviceConstants
                    .DEVICE_LIGHTBELT://灯带

                break;


            case DeviceConstants
                    .DEVICE_COLD_WARM_SPOTLIGHTS://冷暖射灯
                break;


            case DeviceConstants
                    .DEVICE_MASTER_WINDOW://主卧窗户
                break;

            case DeviceConstants
                    .DEVICE_LIVING_CURTAIN://客厅布帘
                new LivingCurtainPopup(getContext(), (LivingCurtain) device).showPopupWindow();
                break;


            case DeviceConstants
                    .DEVICE_SECONDARY_CURTAIN://次卧单边帘
                break;

            case DeviceConstants
                    .DEVICE_KITCHEN_CURTAINS://厨房卷帘
                break;

            case DeviceConstants
                    .DEVICE_PERIPHERAL_SECURITY://外围安防
                break;

            case DeviceConstants
                    .DEVICE_LIVING_MUSIC://客厅音乐
                break;

            case DeviceConstants
                    .DEVICE_TV://电视
                break;

            case DeviceConstants
                    .DEVICE_AIRCONDITIONER://空调
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
