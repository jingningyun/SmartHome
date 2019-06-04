package com.jk184.smarthome.app;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.onAdaptListener;

/**
 * Created by jingning on 2019/5/22 0022 18:01
 * E-Mail Address：jingning1101@163.com
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
