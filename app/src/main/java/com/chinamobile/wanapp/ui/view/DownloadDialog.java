package com.chinamobile.wanapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.http.ProgressListener;
import com.chinamobile.wanapp.http.ProgressResponseBody;
import com.chinamobile.wanapp.http.RetrofitService;
import com.chinamobile.wanapp.utils.LogUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DownloadDialog extends Dialog {

    public Handler  handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    float progress  = (float) msg.obj;
                    if (progressBar!=null){
                        progressBar.setProgress((int) (progress*100));
                    }

                    if (progress_txt!=null){
                        progress_txt.setText((int) (progress*100)+"");
                    }

                    if (progress>=1){//下载完成
                        title.setText("下载完成");//跳转安装页面
                        File loadFile = new File(Environment.getExternalStorageDirectory()+"/wandownload", name);
                        installApk(loadFile);
                        dismiss();
                    }

                    break;
                case 201:
                    if (title!=null){
                        title.setText("下载错误");
                    }

                    break;

            }
            super.handleMessage(msg);
        }
    };


    /**
     * 跳转APP安装页面
     * @param file
     */
    public void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                if (getContext().getPackageManager().canRequestPackageInstalls()){
                    Uri apkUri = FileProvider.getUriForFile(getContext(), "com.chinamobile.wanapp", file);  //包名.fileprovider
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");

                }else {
                    startInstallPermissionSettingActivity(getContext());
                }
            }else {
                Uri apkUri = FileProvider.getUriForFile(getContext(), "com.chinamobile.wanapp", file);  //包名.fileprovider
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            }


        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        getContext().startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        context.startActivity(intent);
    }




    public DownloadDialog(@NonNull Context context,TaskData taskData) {
        super(context, R.style.normal_dialog);
        this.taskData = taskData;


    }

    public DownloadDialog(@NonNull Context context) {
        super(context, R.style.normal_dialog);
    }

    private TextView title,progress_txt;
    private ProgressBar progressBar;

    private TaskData taskData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_progress);
        title = findViewById(R.id.title);
        progress_txt = findViewById(R.id.progress_txt);
        progressBar = findViewById(R.id.progress);

        title.setText("下载中...");
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        Window window = this.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

        downloadAPK();

    }


    String downloadurl = "";
    String name = "";
    private void downloadAPK(){
       /* File loadFile = new File(Environment.getExternalStorageDirectory()+"/wandownload", "downloaddemo.apk");
        installApk(loadFile);*/
       if (taskData!=null){
           if ("0".equals(taskData.getJobStr().getMarket())){
               downloadurl = taskData.getJobStr().getMarketUrl();
               name = taskData.getJobStr().getMarketApk()+".apk";
           }else {
               downloadurl = taskData.getJobStr().getAppUrl();
               name = taskData.getJobStr().getAppApk()+".apk";
           }

       }

       if (TextUtils.isEmpty(downloadurl)||TextUtils.isEmpty(name)){
           Toast.makeText(getContext(),"数据为空",Toast.LENGTH_SHORT).show();
       }else {
           LogUtils.e("Download","下载链接  ： "+downloadurl);
           fileDownLoad(downloadurl,name,handler);
       }

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
                okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder().retryOnConnectionFailure(true).addNetworkInterceptor(new okhttp3.Interceptor() {
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
                                    String files = Environment.getExternalStorageDirectory()+"/wandownload";
                                    File pfile = new File(files);
                                    if (!pfile.exists()){
                                        pfile.mkdir();
                                    }
                                    File loadFile = new File(pfile, name);//保存路径与文件名
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
}
