package com.jk184.smarthome.app.mvp;

import android.database.sqlite.SQLiteDatabase;

import com.jk184.smarthome.app.http.HttpFactory;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class ModelImpl implements IModel {

    private CompositeDisposable compositeDisposable =new CompositeDisposable();


    protected <T> T createRetorfitService(Class<T> service) {
        return HttpFactory.instance().createApiService(service);
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    /**add Disposable*/
    protected void addDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    /**add Disposable*/
    protected void addAllDisposable(Disposable... disposables){
        compositeDisposable.addAll(disposables);
    }

    /**取消掉所有的订阅*/
    private void disposedAll(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
        compositeDisposable.clear();
    }


    //其余一些数据操作的封装....

    @Override
    public void onRelease() {
        disposedAll();
    }

}
