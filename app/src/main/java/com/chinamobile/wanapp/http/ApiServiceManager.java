package com.chinamobile.wanapp.http;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import com.chinamobile.wanapp.BuildConfig;
import com.chinamobile.wanapp.utils.LogUtils;
import com.chinamobile.wanapp.utils.MD5Util;
import com.chinamobile.wanapp.utils.UserManager;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
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

    private static final String GET_OPENAPP = "User/AppOpenImage";

    private static final String GET_TASKLIST = "User/AppHomeTaskGet";

    private static final String GET_TASK_DETAIL = "User/AppHomeTaskGetTwo";

    private static final String GET_SIGN_TASK = "User/SignInSelc";

    private static final String SING_APP = "User/SignInInse";

    private static final String TASK_COMPLETION = "User/TaskFinish";

    private static final String USER_INVITECASE = "User/InviteCase";

    private static final String USER_EVERY_WHEALTH = "User/EverydayWeal";

    private static final String TASK_FINISH_DETAIL = "User/TaskFinishDetail";

    private static final String TASK_FINISH_TOTAL = "User/TaskFinishTotal";


    /**
     * 登录注册接口
     * @param sno IMEI号
     * @param response
     */

    public static void userRegistApp(String sno, HttpResponse response) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("sno", sno);
        stringMap.put("mobile", "");
        stringMap.put("regIp", getLocalIpAddress());
        stringMap.put("status", "true");
        stringMap.put("create_time", getTime());
        stringMap.put("update_time", getTime());
        stringMap.put("invited_by", "11");
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
        stringMap.put("regIp", getLocalIpAddress());
        stringMap.put("status", "true");
        stringMap.put("create_time", getTime());
        stringMap.put("update_time", getTime());
        stringMap.put("invited_by", "11");
        doGet(GET_HOME,stringMap, new HttpCallBack(response));
    }




    /**
     *获取开屏数据
     * @param id
     * @param response
     */
    public static void getAPPOpen(String id,HttpResponse response){
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("sno", id);
        doGet(GET_OPENAPP,stringMap, new HttpCallBack(response));
    }


    /**
     *获取列表
     * @param mid 列表标志
     * @param response
     */
    public static void getDataList(String mid,HttpResponse response){
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("mid", mid);
        stringMap.put("uid", "");
        stringMap.put("count", "20");
        stringMap.put("count_page", "");
        doGet(GET_TASKLIST,stringMap, new HttpCallBack(response));
    }


    /**
     *获取列表
     * @param mid 列表标志
     * @param response
     */
    public static void getDataList(String mid,int page,HttpResponse response){
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("mid", mid);
        stringMap.put("uid", "");
        stringMap.put("count_page", page+"");
        stringMap.put("count", "20");
        doGet(GET_TASKLIST,stringMap, new HttpCallBack(response));
    }

    /**
     * 获取任务详情
     */
    public static void getTaskDetail(String fid,HttpResponse response){
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("uid", UserManager.getInstance().getId());
        stringMap.put("fid", fid);
        stringMap.put("count_end", "20");
        doGet(GET_TASK_DETAIL,stringMap, new HttpCallBack(response));
    }


    /**
     * 获取签到详情
     * @param response
     */
    public static void getSignData(HttpResponse response){
        Map<String,String> stringStringMap = new HashMap<>();
        stringStringMap.put("uid", UserManager.getInstance().getId());
        doGet(GET_SIGN_TASK,stringStringMap, new HttpCallBack(response));
    }


    /**
     * 签到
     * @param response
     */
    public static void getSign(HttpResponse response){
        Map<String,String> stringStringMap = new HashMap<>();
        stringStringMap.put("uid", UserManager.getInstance().getId());
        doGet(SING_APP,stringStringMap, new HttpCallBack(response));
    }

    /**
     * 任务完成
     */
    public static void getTaskCompletion(String jid,String jz_gain,String remark,String snap_url,String status,String eid,HttpResponse response){
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("uid",UserManager.getInstance().getId());
        stringMap.put("jid",jid);
        stringMap.put("jzGain",jz_gain);
        stringMap.put("remark",remark);
        stringMap.put("snapUrl",snap_url);
        stringMap.put("status",status);//免审核1  需要审核0
        stringMap.put("eid",eid);
        stringMap.put("submitTimeStr",getData());
        stringMap.put("approveTimesStr",getData());
        doPost(TASK_COMPLETION,stringMap, new HttpCallBack(response));

    }

    /**
     * 获取邀请人情况
     */
    public static void getUserInviteCase(HttpResponse response){
        Map<String,String> map = new HashMap<>();
        map.put("uid",UserManager.getInstance().getId());
        doGet(USER_INVITECASE,map, new HttpCallBack(response));

    }

    /**
     * 获取每日福利
     */
    public static void getEveryDay(HttpResponse response){
        long[] longs = getToday();

        Map<String,String> map = new HashMap<>();
        map.put("uid",UserManager.getInstance().getId());
        map.put("ruleName","每日福利");
        map.put("submitTime",longs[0]+"");
        map.put("approveTime",longs[1]+"");
        doGet(USER_EVERY_WHEALTH,map, new HttpCallBack(response));
    }

    /**
     * 任务完成详情
     * @param eid
     * @param response
     */
    public static void getTaskCompletionDetail(String eid,HttpResponse response){
        Map<String,String> map = new HashMap<>();
        map.put("uid",UserManager.getInstance().getId());
        map.put("eid",eid);
        map.put("submitTime",getThisWeekStart());
        map.put("approveTime",getThisWeekStart());
        doGet(TASK_FINISH_DETAIL,map, new HttpCallBack(response));

    }



    /**
     * 任务完成统计
     */
    public static void getTaskFinishTotal(String eid,HttpResponse response){
        Map<String,String> map = new HashMap<>();
        map.put("uid",UserManager.getInstance().getId());
        map.put("eid",eid);
        map.put("submitTime",getThisWeekStart());
        map.put("approveTime",getThisWeekEnd());
        doGet(TASK_FINISH_TOTAL,map, new HttpCallBack(response));
    }


























    private static void doGet(String action,Map<String, String> stringMap, Observer<ResponseBody> consumer) {
        String data = getData();
        stringMap.put("timestamp", data);
        stringMap.put("code", getCode(data));
        Observable<ResponseBody> observable = getHttpService().create(RetrofitService.class).getGetRequest(action,stringMap);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

    }




    private static void doPost(String action,Map<String,String> stringMap,Observer<ResponseBody> consumer){
        String data = getData();
        stringMap.put("timestamp", data);
        stringMap.put("code", getCode(data));

        Observable<ResponseBody> observable = getHttpService().create(RetrofitService.class).getPostRequest(action,stringMap);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

    }

    private static void doPostTest(String action,String eid,String jid,String jz_gain,String remark,String snap_url,String status,Observer<ResponseBody> consumer){
        String data = getData();
       // stringMap.put("timestamp", data);
        //stringMap.put("code", getCode(data));

        Observable<ResponseBody> observable = getHttpService().create(RetrofitService.class).getPostTestRequest(action,UserManager.getInstance().getId(),eid,data,getCode(data),jid,jz_gain,remark,snap_url,status,data,data);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

    }

    private static String getData() {
        Date date = new Date();
        return date.getTime() + "";
    }

    private static long[] getToday(){
        long current=new Date().getTime();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数

        long[] longs = new long[]{zero,twelve};

        return  longs;
    }

    /**
     * 获取一周 起始时间
     * @return
     */
    private static String getThisWeekStart(){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyyMMdd", Locale. getDefault());
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar. DAY_OF_WEEK) - 1;
        if (day_of_week == 0 ) {
            day_of_week = 7 ;
        }
        cal.add(Calendar.DATE , -day_of_week + 1 );


        return  simpleDateFormat.format(cal.getTime()) + "000000";
    }

    /**
     * 获取一周结束时间
     * @return
     */

    private static String getThisWeekEnd(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyyMMdd", Locale. getDefault());
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar. DAY_OF_WEEK) - 1;
        if (day_of_week == 0 ) {
            day_of_week = 7 ;
        }
        cal.add(Calendar.DATE , -day_of_week + 7 );
        return simpleDateFormat.format(cal.getTime()) + "235959";

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


    /**
     *
     * @param url   完整的下载链接
     * @param fileName   带后缀的文件名
     */
    public static void fileDownLoad(final String url, String fileName, final Handler handler) {
       /* int i = url.lastIndexOf("/");
        final String title = url.substring(0, i + 1);*/
        final String name = fileName;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://codeload.github.com/");//传入服务器BaseUrl
                okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder().addNetworkInterceptor(new okhttp3.Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        return response.newBuilder().body(new ProgressResponseBody(response.body(), new ProgressListener() {
                            @Override
                            public void onProgress(long progress, long total, boolean done) {
                                LogUtils.e("Download","progress : "+progress+"         total : "+total);

                                if (handler!=null){
                                    Message message = Message.obtain();
                                    message.what = 200;
                                    double to = total;
                                    double pr = progress;
                                    float pro = (float) (pr/to);
                                    LogUtils.e("Download","pro : "+pro);
                                    message.obj = pro;
                                    handler.sendMessage(message);
                                }

                            }
                        })).build();
                    }
                }).build();
                RetrofitService apiService = builder.client(client).build().create(RetrofitService.class);
                Call<ResponseBody> call = apiService.getFile(url);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final retrofit2.Response<ResponseBody> response) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (response == null || response.body() == null) {//空地址
                                        LogUtils.e("Download","空地址");
                                        if (handler!=null){
                                            Message message = Message.obtain();
                                            message.what = 201;//
                                            handler.sendMessage(message);
                                        }
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
                                    LogUtils.e("Download","下载失败 ："+e.getMessage());
                                    if (handler!=null){
                                        Message message = Message.obtain();
                                        message.what = 201;
                                        handler.sendMessage(message);
                                    }
                                }

                            }
                        }).start();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();//下载失败
                        LogUtils.e("Download","onFailure ："+t.getMessage());
                        if (handler!=null){
                            Message message = Message.obtain();
                            message.what = 201;
                            handler.sendMessage(message);
                        }
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
                LogUtils.e("Http",message);
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
