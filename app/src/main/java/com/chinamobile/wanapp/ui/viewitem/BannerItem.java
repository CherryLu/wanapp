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
        imageArrary.add("http://img.zcool.cn/community/0117e2571b8b246ac72538120dd8a4.jpg@1280w_1l_2o_100sh.jpg");
        imageArrary.add("http://img07.tooopen.com/images/20170316/tooopen_sy_201956178977.jpg");
        imageArrary.add("http://img0.imgtn.bdimg.com/it/u=3972169330,4211815266&fm=214&gp=0.jpg");

        List<String> titleArrary = new ArrayList<>();
        titleArrary.add("标题一");
        titleArrary.add("标题二");
        titleArrary.add("标题三");

        Banner banner = holder.getConvertView().findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(imageArrary);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.RotateDown);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titleArrary);
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
