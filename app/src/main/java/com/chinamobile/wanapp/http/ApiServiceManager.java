package com.chinamobile.wanapp.http;

import android.os.Environment;
import android.util.Log;

import com.chinamobile.wanapp.BuildConfig;
import com.chinamobile.wanapp.baen.BaseBean;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.utils.MD5Util;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/8/6.
 */

public class ApiServiceManager {
    private static final String USER_REGIST = "User/register";

    private static final String GET_HOME = "User/UserHomeSel";

    /**
     * 登录注册接口
     * @param sno IMEI号
     * @param response
     */

    public static void userRegistApp(String sno, HttpResponse response) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("sno", sno);
        stringMap.put("id", sno);
        stringMap.put("mobile", "");
        doGet(USER_REGIST,stringMap, new HttpCallBack(response));

    }

    /**
     * 获取首页数据
     * @param id
     * @param response
     */
    public static void getHomeData(String id, HttpResponse response){
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("id", id);
        doGet(GET_HOME,stringMap, new HttpCallBack(response));
    }


    private static void doGet(String action,Map<String, String> stringMap, Observer<BaseBean> consumer) {
        String data = getData();
        stringMap.put("regIp", getLocalIpAddress());
        stringMap.put("status", "true");
        stringMap.put("create_time", getTime());
        stringMap.put("update_time", getTime());
        stringMap.put("invited_by", "11");
        stringMap.put("timestamp", data);
        stringMap.put("code", getCode(data));
        Observable<BaseBean> observable = getHttpService().create(RetrofitService.class).getGetRequest(action,stringMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

    }

    private static String getData() {
        Date date = new Date();
        return date.getTime() + "";
    }

    private static String getCode(String data) {
        String key = "yioye@sina.cn";
        String before = key + data;
        return MD5Util.lowerMD5(before);
    }

    private static String getTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);

    }

    private static void doPost(String type, Observer<BaseItem> consumer) {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("key", "41f0c2e26ac4b3bf0b965fb8e70b6449");
        Observable<BaseItem> observable = getHttpService().create(RetrofitService.class).getPostRequest(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

    }


    public static void fileDownLoad(final String url) {
        int i = url.lastIndexOf("/");
        final String title = url.substring(0, i + 1);
        final String name = url.substring(i + 1, url.length());
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://s9.rr.itc.cn/r/wapChange/20165_5_23/");//传入服务器BaseUrl
                okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder().addNetworkInterceptor(new okhttp3.Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        return response.newBuilder().body(new ProgressResponseBody(response.body(), new ProgressListener() {
                            @Override
                            public void onProgress(long progress, long total, boolean done) {

                            }
                        })).build();
                    }
                }).build();
                RetrofitService apiService = builder.client(client).build().create(RetrofitService.class);
                Call<ResponseBody> call = apiService.getFile(name);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final retrofit2.Response<ResponseBody> response) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (response == null || response.body() == null) {//空地址

                                        return;
                                    }
                                    InputStream is = response.body().byteStream();
                                    File loadFile = new File(Environment.getExternalStorageDirectory(), name);//保存路径与文件名
                                    FileOutputStream fos = new FileOutputStream(loadFile);
                                    BufferedInputStream bis = new BufferedInputStream(is);
                                    byte[] bytes = new byte[1024];
                                    int len;
                                    while ((len = bis.read(bytes)) != -1) {
                                        fos.write(bytes, 0, len);
                                        fos.flush();
                                    }
                                    fos.close();
                                    bis.close();
                                    is.close();
                                } catch (IOException e) {
                                    e.printStackTrace();//下载失败
                                }

                            }
                        }).start();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();//下载失败
                    }
                });
            }
        }).start();

    }


    private static Retrofit getHttpService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }


    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("HttpClient", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        builder.retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        return builder.build();
    }


    private static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;

    }

}
