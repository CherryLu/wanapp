package com.chinamobile.wanapp.http;

import com.chinamobile.wanapp.baen.BaseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


/**
 * Created by Administrator on 2018/8/9.
 */

public class HttpCallBack implements Observer<ResponseBody> {
    private HttpResponse response;

    public HttpCallBack(HttpResponse response) {
        this.response = response;

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseBody value) {
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
