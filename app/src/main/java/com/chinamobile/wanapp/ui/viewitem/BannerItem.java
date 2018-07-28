package com.chinamobile.wanapp.ui.viewitem;

import android.content.Context;
import android.widget.ImageView;

import com.chinamobile.wanapp.R;
import com.chinamobile.wanapp.baen.BaseItem;
import com.chinamobile.wanapp.utils.GlideUtil;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

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
