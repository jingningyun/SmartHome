package com.jk184.smarthome.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jk184.smarthome.R;
import com.jk184.smarthome.app.BaseFragment;
import com.jk184.smarthome.common.view.CircleImageView;
import com.jk184.smarthome.mine.UserInfoActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jingning on 2019/5/23 0023 14:43
 * E-Mail Address：jingning1101@163.com
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.ivHeadBg)
    ImageView ivHeadBg;
    @BindView(R.id.civHead)
    CircleImageView civHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNo)
    TextView tvNo;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.civHead, R.id.tvName, R.id.tvNo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civHead:
            case R.id.tvName:
            case R.id.tvNo:
                openActivity(UserInfoActivity.class);
                break;
        }
    }
}
