package com.jk184.smarthome.app.mvp;


public interface IPresenter<V extends IView> {

    void attachView(V view);

    void detatchView();
}
