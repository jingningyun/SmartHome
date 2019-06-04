package com.jk184.smarthome.app.http;

/**
 * Created by Administrator on 2018/3/19.
 */

public class BaseResponseListener<T> implements ResponseListener<T>{

    @Override
    public void onComplete() {
    }

    @Override
    public void onResponse(T data) {
    }

    @Override
    public void onFailed(Throwable e) {
    }
}
