package com.chinamobile.wanapp.ui.viewitem;

import android.content.Context;
import android.widget.ImageView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.utils.GlideUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95470 on 2018/7/28.
 */

public class BannerItem implements ItemViewDelegate<BaseItem>,OnBannerListener {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_banner;
    }

    @Override
    public boolean isForViewType(BaseItem item, int position) {
        if (item.getType()==BaseItem.ITEM_BANNER){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, BaseItem baseItem, int position) {
        List<String> imageArrary = new ArrayList<>();
        for (int i=0;i<baseItem.getDataList().size();i++){
            imageArrary.add(baseItem.getDataList().get(i).getImgUrl());
        }



        Banner banner = holder.getConvertView().findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageArrary);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.RotateDown);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


    }

    @Override
    public void OnBannerClick(int position) {

    }

    class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideUtil.loadImageView(context, (String) path,imageView);
        }
    }
}
