package com.jk184.smarthome.app.mvp;

import android.content.Context;
import android.os.Handler;


public abstract class PresenterImpl<V extends IView, M extends IModel> implements IPresenter<V> {

    private V mIView;
    private M mIModel;
    private Handler mHandler;
    private Context mContext;

    public PresenterImpl(Context context){
        this.mContext = context;
        mIModel = initModel();
        mHandler = new Handler();
    }

    public abstract M initModel();

    @Override
    public void attachView(V view) {
        mIView = view;
    }

    protected Handler getHandler(){
        return mHandler;
    }

    protected Context getContext(){
        return mContext;
    }

    @Override
    public void detatchView() {
        release();
    }

    private void release(){

        //Model层数据释放
        if (mIModel != null) {
            mIModel.onRelease();
            mIModel = null;
        }

        //Presenter层数据释放
        onRelease();

        //View层数据释放
        mIView = null;
    }

    public V getView() {
        return mIView;
    }

    public M getModel() {
        return mIModel;
    }

    protected void onRelease() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        mContext = null;
        mIModel = null;
    }

}
