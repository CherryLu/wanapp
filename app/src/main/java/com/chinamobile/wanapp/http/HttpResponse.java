package com.chinamobile.wanapp.http;


import com.chinamobile.wanapp.baen.BaseBean;

/**
 * Created by Administrator on 2018/8/9.
 */

public interface HttpResponse{

    void onNext(BaseBean baseItem);
    void onError(Throwable e);


}
