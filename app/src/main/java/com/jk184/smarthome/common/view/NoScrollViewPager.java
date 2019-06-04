package com.jk184.smarthome.common.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by jingning on 2019/5/23 0023 10:31
 * E-Mail Address：jingning1101@163.com
 */
public class NoScrollViewPager extends ViewPager {

    private boolean isScroll = false;

    public NoScrollViewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isScroll){
            return super.onTouchEvent(ev);
        }else{
            return true;
        }
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }
}
