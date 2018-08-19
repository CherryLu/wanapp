package com.chinamobile.wanapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.TaskData;
import com.chinamobile.wanapp.utils.GlideUtil;
import com.chinamobile.wanapp.utils.LogUtils;
import com.chinamobile.wanapp.utils.Nagivator;
import com.chinamobile.wanapp.utils.OssUtils;
import com.chinamobile.wanapp.utils.ScreenUtil;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/19.
 */

public class UploadActivity extends TakePhotoActivity {


    @Bind(R.id.back_image)
    ImageView backImage;
    @Bind(R.id.main_title)
    TextView mainTitle;
    @Bind(R.id.right_txt)
    TextView rightTxt;
    @Bind(R.id.right_image)
    ImageView rightImage;
    @Bind(R.id.app_pic)
    ImageView appPic;
    @Bind(R.id.app_name)
    TextView appName;
    @Bind(R.id.app_size)
    TextView appSize;
    @Bind(R.id.app_lable1)
    TextView appLable1;
    @Bind(R.id.app_lable2)
    TextView appLable2;
    @Bind(R.id.getmoney)
    TextView getmoney;
    @Bind(R.id.steps_layouy)
    LinearLayout stepsLayouy;
    @Bind(R.id.image_layout)
    LinearLayout imageLayout;
    @Bind(R.id.download)
    Button download;
    @Bind(R.id.image_yulan_layout)
    LinearLayout imageYulanLayout;

    private TaskData currentData;
    private OssUtils ossUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);

        currentData = (TaskData) getIntent().getSerializableExtra("FT");
        ininTitleData(currentData);
        initUploadLayout(null);
    }

    private void ininTitleData(TaskData taskData) {
        GlideUtil.loadImageView(this, taskData.getIconUrl(), appPic);
        appName.setText(taskData.getTitle());
        String[] str = taskData.getJobTags().split(";");

        if (str.length > 0) {
            appLable1.setText(str[0]);
        }
        if (str.length > 1) {
            appLable2.setText(str[1]);
        }
        if (taskData.getSampleimgUrl() != null) {
            String[] strings = taskData.getSampleimgUrl().split(";");
            initLayout(strings);
        }


    }

    private void initLayout(String[] images) {
        imageLayout.removeAllViews();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = (ImageView) View.inflate(this, R.layout.moddle_image, null);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtil.dip2px(this, 200), ScreenUtil.dip2px(this, 300), 1));
            if (i == 0) {
                imageView.setPadding(0, 0, ScreenUtil.dip2px(this, 5), 0);
            } else {
                imageView.setPadding(ScreenUtil.dip2px(this, 5), 0, 0, 0);
            }

            GlideUtil.loadImageView(this, images[i], imageView);

            imageLayout.addView(imageView);
        }
    }


    private void initUploadLayout(List<String> pics) {
        imageYulanLayout.removeAllViews();
        if (pics==null){
            View view = View.inflate(this, R.layout.moddle_upload_image, null);
            view.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtil.dip2px(this,120), ScreenUtil.dip2px(this,120), 1));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//开启相册
                    getTakePhoto().onEnableCompress(getCompressConfig(),true);
                    getTakePhoto().onPickMultiple(3);
                    //getTakePhoto().onPickFromDocumentsWithCrop(getImageUrl(),getCropOptions());
                }
            });

            imageYulanLayout.addView(view);

            return;
        }

        for (int i = 0; i < pics.size(); i++) {
            ImageView imageView = (ImageView) View.inflate(this, R.layout.moddle_image, null);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtil.dip2px(this,100), ScreenUtil.dip2px(this,150), 1));
            if (i == 0) {
                imageView.setPadding(0, 0, ScreenUtil.dip2px(this, 5), 0);
            } else {
                imageView.setPadding(ScreenUtil.dip2px(this, 5), 0, 0, 0);
            }

            GlideUtil.loadImageView(this, pics.get(i), imageView);
            imageYulanLayout.addView(imageView);
        }

        View view = View.inflate(this, R.layout.moddle_upload_image, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtil.dip2px(this,100), ScreenUtil.dip2px(this,150), 1));
        view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//开启相册
                    getTakePhoto().onEnableCompress(getCompressConfig(),true);
                    getTakePhoto().onPickMultiple(3);
                    //getTakePhoto().onPickFromDocumentsWithCrop(getImageUrl(),getCropOptions());
                }
            });

        imageYulanLayout.addView(view);

    }


    private CropOptions getCropOptions(){
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        return builder.create();
    }


    private CompressConfig getCompressConfig(){

        CompressConfig config = new CompressConfig.Builder()
                .setMaxSize(102400)
                .setMaxPixel(400)
                .enableReserveRaw(true)
                .create();

        return config;
    }


    private Uri getImageUrl(){
        File file = new File(Environment.getExternalStorageDirectory(),
                "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
            if (!mkdirs) {
                Toast.makeText(this,"文件目录创建失败",Toast.LENGTH_SHORT);
            }
        }
        Uri imageUri = Uri.fromFile(file);

        return imageUri;
    }

    @OnClick({R.id.back_image, R.id.download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                Nagivator.finishActivity(this);
                break;
            case R.id.download:

                break;
        }
    }

    List<String> pics;
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (pics==null){
            pics = new ArrayList<>();

        }else {
           // pics.clear();
        }
       ArrayList<TImage> tImages =  result.getImages();
        for (int i =0;i<tImages.size();i++){
            pics.add(tImages.get(i).getOriginalPath());
        }

        initUploadLayout(pics);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }


    @Override
    public void takeCancel() {
        super.takeCancel();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
