package com.jk184.smarthome.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jk184.smarthome.R;
import com.jk184.smarthome.app.BaseFragment;
import com.jk184.smarthome.common.adapter.PagerAdapter;
import com.jk184.smarthome.home.adapter.DeviceModelListAdapter;
import com.jk184.smarthome.home.adapter.DeviceTypeListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jingning on 2019/5/23 0023 14:43
 * E-Mail Address：jingning1101@163.com
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.vpRoom)
    ViewPager vpRoom;
    @BindView(R.id.tvFloor)
    TextView tvFloor;
    @BindView(R.id.tvTemperature)
    TextView tvTemperature;
    @BindView(R.id.tvPM)
    TextView tvPM;
    @BindView(R.id.llweather)
    LinearLayout llweather;
    @BindView(R.id.stlRoom)
    SlidingTabLayout stlRoom;
    @BindView(R.id.llHead)
    LinearLayout llHead;
    @BindView(R.id.rvType)
    RecyclerView rvType;
    @BindView(R.id.rvModel)
    RecyclerView rvModel;

    Unbinder unbinder;

    private PagerAdapter mPagerAdapter;
    private SparseArray<Fragment> mFragments = new SparseArray<>();

    private String[] mTitles = new String[]{"客厅", "餐厅", "厨房", "主卧", "次卧"};//ui测试，后续数据来自服务器

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initRoom();
        initType();
        initModel();

    }

    private void initRoom() {
        mFragments.clear();
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.append(i, RoomFragment.newInstance());
        }
        mPagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), mFragments);
        vpRoom.setAdapter(mPagerAdapter);
        stlRoom.setViewPager(vpRoom, mTitles);
        vpRoom.setCurrentItem(0);
    }

    private void initType() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvType.setLayoutManager(manager);
        List<Integer> types = new ArrayList<Integer>();
        for (int i = 1; i < 9; i++) {
            types.add(i);
        }
        DeviceTypeListAdapter deviceTypeListAdapter = new DeviceTypeListAdapter(R.layout.item_device_type, types);
        rvType.setAdapter(deviceTypeListAdapter);
    }

    private void initModel() {
        List<Integer> models = new ArrayList<Integer>();
        for (int i = 1; i < 6; i++) {
            models.add(i);
        }
        rvModel.setLayoutManager(new GridLayoutManager(getContext(), models.size()));//为了均分5个布局，后续要改
        DeviceModelListAdapter deviceModelListAdapter = new DeviceModelListAdapter(R.layout.item_device_model, models);
        rvModel.setAdapter(deviceModelListAdapter);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.llFloor, R.id.llHead})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llFloor:
                break;
            case R.id.llHead:
                break;
        }
    }


}
