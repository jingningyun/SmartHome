package com.jk184.smarthome.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/19.
 */

@SuppressLint("AppCompatCustomView")
public class CountDownView extends TextView {

    private CountDownCallBack mCallback;

    private boolean hasStartCountDown;

    private int mCurrentDown;

    private Handler mHandler = new Handler();

    public CountDownView(Context context) {
        super(context);
        init();
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setClickable(true);
    }

    public void startCountDown(int maxSecound, CountDownCallBack callBack) {
        if (hasStartCountDown) {
            mHandler.removeCallbacks(mCountDownRunnable);
        }
        mCallback = callBack;
        setEnabled(false);
        hasStartCountDown = true;
        mCurrentDown = maxSecound;
        mHandler.postDelayed(mCountDownRunnable, 1000);
    }

    Runnable mCountDownRunnable = new Runnable() {
        @Override
        public void run() {
            mCurrentDown = mCurrentDown - 1;
            mHandler.removeCallbacks(mCountDownRunnable);
            if (mCurrentDown > 0) {//继续倒计时
                if (mCallback != null) {
                    mCallback.onCountDown(mCurrentDown);
                }
                mHandler.postDelayed(mCountDownRunnable, 1000);
            } else {//倒计时完成
                hasStartCountDown = false;
                setEnabled(true);
                if (mCallback != null) {
                    mCallback.onCountComplate();
                }
            }
        }
    };

    /**停止倒计时*/
    public void stopContDown(){
        mHandler.removeCallbacks(mCountDownRunnable);
        if(mCallback != null){
            mCallback.onCountComplate();
        }
    }

    public interface CountDownCallBack {

        /**
         * 正在倒计时
         */
        void onCountDown(int second);

        /**
         * 倒计时完成
         */
        void onCountComplate();
    }

    public void release() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}
