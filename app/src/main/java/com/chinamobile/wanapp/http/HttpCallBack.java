package com.chinamobile.wanapp.http;

import com.chinamobile.wanapp.baen.BaseBean;
import com.chinamobile.wanapp.baen.BaseItem;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Administrator on 2018/8/9.
 */

public class HttpCallBack implements Observer<BaseBean> {
    private HttpResponse response;

    public HttpCallBack(HttpResponse response) {
        this.response = response;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseBean value) {
        if (response!=null){
            response.onNext(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (response!=null){
            response.onError(e);
        }
    }

    @Override
    public void onComplete() {

    }
}
