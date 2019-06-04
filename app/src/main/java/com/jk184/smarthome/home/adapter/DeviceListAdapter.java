package com.jk184.smarthome.home.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.jk184.smarthome.R;
import com.jk184.smarthome.common.constants.DeviceConstants;
import com.jk184.smarthome.home.adapter.holder.AirConditionerHodler;
import com.jk184.smarthome.home.adapter.holder.BaseDeviceHodler;
import com.jk184.smarthome.home.adapter.holder.ColdWarmSpotlightsHodler;
import com.jk184.smarthome.home.adapter.holder.KitchenCurtainsHodler;
import com.jk184.smarthome.home.adapter.holder.LightTubeHodler;
import com.jk184.smarthome.home.adapter.holder.LightbeltHodler;
import com.jk184.smarthome.home.adapter.holder.LivingCurtainHodler;
import com.jk184.smarthome.home.adapter.holder.LivingMusicHodler;
import com.jk184.smarthome.home.adapter.holder.MasterWindowHodler;
import com.jk184.smarthome.home.adapter.holder.PeripheralSecurityHodler;
import com.jk184.smarthome.home.adapter.holder.SecondaryCurtainHodler;
import com.jk184.smarthome.home.adapter.holder.SpotLightHodler;
import com.jk184.smarthome.home.adapter.holder.TvHodler;
import com.jk184.smarthome.home.bean.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingning on 2019/5/27 0027 18:01
 * E-Mail Address：jingning1101@163.com
 */
public class DeviceListAdapter extends RecyclerView.Adapter<BaseDeviceHodler> {

    private List<Device> devices = new ArrayList<Device>();
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public DeviceListAdapter(Context context, List<Device> devices) {
        this.mContext = context;
        this.devices.addAll(devices);
    }

    @NonNull
    @Override
    public BaseDeviceHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        BaseDeviceHodler hodler = null;
        switch (viewType) {
            case DeviceConstants
                    .DEVICE_LIGHTTUBE://灯筒
                View lightTubeView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_lighttube, viewGroup, false);
                hodler = new LightTubeHodler(mContext, lightTubeView);
                break;

            case DeviceConstants
                    .DEVICE_SPOTLIGHT://射灯
                View spotLightView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_spotlight, viewGroup, false);
                hodler = new SpotLightHodler(mContext, spotLightView);
                break;


            case DeviceConstants
                    .DEVICE_LIGHTBELT://灯带
                View lightbeltView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_lightbelt, viewGroup, false);
                hodler = new LightbeltHodler(mContext, lightbeltView);
                break;


            case DeviceConstants
                    .DEVICE_COLD_WARM_SPOTLIGHTS://冷暖射灯
                View coldWarmSpotlightsView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_cold_warm_spotlights, viewGroup, false);
                hodler = new ColdWarmSpotlightsHodler(mContext, coldWarmSpotlightsView);
                break;


            case DeviceConstants
                    .DEVICE_MASTER_WINDOW://主卧窗户
                View masterWindowView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_master_window, viewGroup, false);
                hodler = new MasterWindowHodler(mContext, masterWindowView);
                break;

            case DeviceConstants
                    .DEVICE_LIVING_CURTAIN://客厅布帘
                View livingCurtainView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_living_curtain, viewGroup, false);
                hodler = new LivingCurtainHodler(mContext, livingCurtainView);
                break;


            case DeviceConstants
                    .DEVICE_SECONDARY_CURTAIN://次卧单边帘
                View secondaryCurtainView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_secondary_curtain, viewGroup, false);
                hodler = new SecondaryCurtainHodler(mContext, secondaryCurtainView);
                break;

            case DeviceConstants
                    .DEVICE_KITCHEN_CURTAINS://厨房卷帘
                View kitchenCurtainsView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_kitchen_curtains, viewGroup, false);
                hodler = new KitchenCurtainsHodler(mContext, kitchenCurtainsView);
                break;

            case DeviceConstants
                    .DEVICE_PERIPHERAL_SECURITY://外围安防
                View peripheralSecurityView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_peripheral_security, viewGroup, false);
                hodler = new PeripheralSecurityHodler(mContext, peripheralSecurityView);
                break;

            case DeviceConstants
                    .DEVICE_LIVING_MUSIC://客厅音乐
                View livingMusicView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_livingmusic, viewGroup, false);
                hodler = new LivingMusicHodler(mContext, livingMusicView);
                break;

            case DeviceConstants
                    .DEVICE_TV://电视
                View tvView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_tv, viewGroup, false);
                hodler = new TvHodler(mContext, tvView);
                break;

            case DeviceConstants
                    .DEVICE_AIRCONDITIONER://空调
                View airConditionerView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_device_air_conditioner, viewGroup, false);
                hodler = new AirConditionerHodler(mContext, airConditionerView);
                break;
        }
        if(getOnItemClickListener() != null){
            final BaseDeviceHodler finalHodler = hodler;
            hodler.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnItemClickListener().onItemClick(DeviceListAdapter.this, v, finalHodler.getLayoutPosition());
                }
            });
        }
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDeviceHodler baseViewHolder, int position) {
        if (baseViewHolder instanceof LightTubeHodler) {
            bindLightTube((LightTubeHodler) baseViewHolder, position);
        }
    }

    //把灯筒的实体和item视图绑定起来
    private void bindLightTube(LightTubeHodler hodler, final int position) {
        hodler.cbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("jingning", "点击了第" + position + " 位置的灯筒 " + isChecked);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return devices.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    @Nullable
    public Device getItem(@IntRange(from = 0) int position) {
        if (position >= 0 && position < devices.size())
            return devices.get(position);
        else
            return null;
    }


    /**
     * Register a callback to be invoked when an item in this RecyclerView has
     * been clicked.
     *
     * @param listener The callback that will be invoked.
     */
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * @return The callback to be invoked with an item in this RecyclerView has
     * been clicked and held, or null id no callback as been set.
     */
    public final OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    /**
     * Interface definition for a callback to be invoked when an item in this
     * RecyclerView itemView has been clicked.
     */
    public interface OnItemClickListener {

        /**
         * Callback method to be invoked when an item in this RecyclerView has
         * been clicked.
         *  @param adapter  the adpater
         * @param view     The itemView within the RecyclerView that was clicked (this
         *                 will be a view provided by the adapter)
         * @param position The position of the view in the adapter.
         */
        void onItemClick(DeviceListAdapter adapter, View view, int position);
    }
}
