package com.jk184.smarthome.app.http;

/**
 * Created by Administrator on 2018/3/19.
 */

public interface ResponseListener<T> {

    void onComplete();

    void onResponse(T data);

    void onFailed(Throwable e);

}
