package com.chinamobile.wanapp.utils;

import android.content.Context;
import android.graphics.Color;


import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.ui.view.LoadingDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 954703125 on 2017/4/18.
 */

public class AlertHelper {
    private SweetAlertDialog pDialog;
    private Context context;
    private LoadingDialog loadingDialog;

    public AlertHelper(Context context){
        this.context = context;
    }

    public void showLoading(){
        loadingDialog = new LoadingDialog(this.context, LoadingDialog.CUSTOM_IMAGE_TYPE);
        loadingDialog.setTitleText("加载中...");
        loadingDialog.setCustomImage(R.drawable.loading);
        loadingDialog.setButtonGone();
        loadingDialog.setCancelable(false);
        loadingDialog.show();
    }

    public boolean isShowing(){
        if(loadingDialog!=null){
            return loadingDialog.isShowing();
        }
        return false;
    }
    public void dissmiss(){
        if(loadingDialog!=null){
            loadingDialog.dismissWithAnimation();
        }
    }

    /**
     * 显示成功
     */
    public void showSuccess(){
        SweetAlertDialog mDialog =new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        mDialog.setCancelable(false);
        mDialog.setTitleText("提示")
                .setContentText("签到成功")
                .setConfirmText("确定")
                .show();

    }

    /**
     * 显示提示
     */
    public void showSTips(String msg){
        SweetAlertDialog mDialog =new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        mDialog.setCancelable(false);
        mDialog.setTitleText("提示")
                .setContentText(msg)
                .setConfirmText("确定")
                .show();

    }

    public void alert(String title, String msg, String sureMsg, final IAlertListener iAlertListener){
        SweetAlertDialog mDialog =new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        mDialog.setCancelable(false);
        mDialog.setTitleText(title)
                .setContentText(msg)
                .setConfirmText(sureMsg)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        if(iAlertListener!=null){
                            iAlertListener.sure();
                        }
                    }
                })
                .show();
    }

    public static  void confirm(String msg, Context context, final IAlertListener iAlertListener ){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("系统消息")
                .setContentText(msg)
                .setCancelText("取消")
                .setConfirmText("确定")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        if(iAlertListener!=null){
                            iAlertListener.sure();
                        }
                    }
                }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                        if(iAlertListener!=null){
                            iAlertListener.cancel();
                        }
                    }
                })
                .show();
    }


    public static  void confirm(String title, String msg, String cancel, String sureMsg, Context context, final IAlertListener iAlertListener ){
        new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .setCancelText(cancel)
                .setConfirmText(sureMsg)
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        if(iAlertListener!=null){
                            iAlertListener.sure();
                        }
                    }
                }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.cancel();
                if(iAlertListener!=null){
                    iAlertListener.cancel();
                }
            }
        })
                .show();
    }


    public void initProgress(){
        initProgress("加载中...");
    }

    public void initProgress(String title){
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(title);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void progress(float progress){
        pDialog.setTitleText("下载进度："+ String.format("%.2f",progress*100)+"%");
    }

    public void progress(String progressMsg){
        pDialog.setTitleText(progressMsg);
    }

    public void changeProgressError(String title, String msg, String surMsg, final IAlertListener iAlertListener ){
        pDialog.setCancelable(false);
        pDialog.setTitleText(title)
                .setContentText(msg)
                .setConfirmText(surMsg)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        if(iAlertListener!=null){
                            iAlertListener.sure();
                        }
                    }
                })
                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
    }

    public void changeProgressErrorCancelable(String title, String msg, String surMsg, final IAlertListener iAlertListener ){
        pDialog.setCancelable(true);
        pDialog.setTitleText(title)
                .setContentText(msg)
                .setConfirmText(surMsg)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        if(iAlertListener!=null){
                            iAlertListener.sure();
                        }
                    }
                })
                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
    }


    public void destoryProgress(){
        pDialog.dismiss();
    }

    public interface IAlertListener{
        public void sure();
        public void cancel();
    }

}
