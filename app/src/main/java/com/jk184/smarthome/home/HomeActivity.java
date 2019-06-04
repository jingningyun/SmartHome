package com.jk184.smarthome.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jk184.smarthome.R;
import com.jk184.smarthome.app.BaseActivity;
import com.jk184.smarthome.common.adapter.PagerAdapter;
import com.jk184.smarthome.common.listener.OnPageSelectedListener;
import com.jk184.smarthome.common.uilts.StatusBarUtil;
import com.jk184.smarthome.common.view.CheckImageView;
import com.jk184.smarthome.common.view.NoScrollViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jingning on 2019/5/22 0022 17:49
 * E-Mail Address：jingning1101@163.com
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.vpMain)
    NoScrollViewPager vpMain;
    @BindView(R.id.menu_ll)
    LinearLayout menuLl;
    @BindView(R.id.vFakeStatus)
    View vFakeStatus;

    private List<View> tableViews;
    private SparseArray<Fragment> fragments = new SparseArray<>();
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setFakeStatus(vFakeStatus);
    }

    @Override
    protected void initView() {
        super.initView();
        initTabs();
        initFragments();
    }

    private void initFragments() {
//        fragments.append(0, MineFragment.newInstance());
        fragments.append(1, HomeFragment.newInstance());
//        fragments.append(2, MineFragment.newInstance());
//        fragments.append(3, MineFragment.newInstance());
        fragments.append(4, MineFragment.newInstance());
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        vpMain.setOffscreenPageLimit(fragments.size());
        vpMain.setAdapter(pagerAdapter);
        vpMain.addOnPageChangeListener(mOnPageChangeListener);
        setTabSelected(1);
    }

    private void initTabs() {
        tableViews = Arrays.asList(findViewById(R.id.tab1_ll),
                findViewById(R.id.tab2_ll),
                findViewById(R.id.tab3_ll),
                findViewById(R.id.tab4_ll),
                findViewById(R.id.tab5_ll));
        for (View tabView : tableViews) {
            tabView.setOnClickListener(this);
        }
    }

    OnPageSelectedListener mOnPageChangeListener = new OnPageSelectedListener() {
        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                setTabSelected(1);
                StatusBarUtil.setColor(HomeActivity.this, getResources().getColor(R.color.main_bg));
            } else {
                setTabSelected(4);
                StatusBarUtil.setColor(HomeActivity.this, getResources().getColor(R.color.white));
            }

        }
    };

    private void setTabSelected(int position) {
        if (tableViews == null) return;
        for (int i = 0; i < tableViews.size(); i++) {
            boolean isChecked = (i == position);
            ViewGroup tabView = (ViewGroup) tableViews.get(i);
            CheckImageView tableImgCk = (CheckImageView) tabView.getChildAt(0);
            TextView tableNameTv = (TextView) tabView.getChildAt(1);
            tableImgCk.setChecked(isChecked);
            tableNameTv.setEnabled(isChecked);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1_ll:
            case R.id.tab3_ll:
            case R.id.tab4_ll:
                toast("该模块正在开发中...");
                break;
            case R.id.tab2_ll:
            case R.id.tab5_ll:
                int pos = tableViews.indexOf(v);
                if (pos == 1) {
                    vpMain.setCurrentItem(0);
                } else {
                    vpMain.setCurrentItem(1);
                }

                break;
        }
    }
}
