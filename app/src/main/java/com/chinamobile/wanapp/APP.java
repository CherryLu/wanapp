package com.chinamobile.wanapp;

import android.app.Application;
import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.chinamobile.wanapp.utils.Constant;

/**
 * Created by Administrator on 2018/8/10.
 */

public class APP extends Application {
    private static OSS oss;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initOSSClient();
    }

    public static OSS getOss() {
        return oss;
    }

    public void initOSSClient() {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(Constant.accessKeyId, Constant.accessKeySecret);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(8); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

        // oss为全局变量，endpoint是一个OSS区域地址
        oss = new OSSClient(context,Constant.ENDPOINT, credentialProvider, conf);

    }



    public static Context getContext() {
        return context;
    }
}
