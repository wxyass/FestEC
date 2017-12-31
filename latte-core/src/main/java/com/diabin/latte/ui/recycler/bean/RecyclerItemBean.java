package com.diabin.latte.ui.recycler.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

/**
 * 使用这种bean 不利于内存释放, 废弃
 *
 * Created by yangwenmin on 2017/10/28.
 */

public class RecyclerItemBean implements MultiItemEntity {

    int itemDataType = 0;// 根据值不同, 展示不同的item布局
    int spanSize = 0;
    int id = 0;
    String text = null;
    String imageUrl = null;
    ArrayList<String> bannerImages = new ArrayList<>();


    public RecyclerItemBean(int itemDataType,
                            int spanSize,
                            int id,
                            String text,
                            String imageUrl,
                            ArrayList<String> bannerImages) {
        this.itemDataType = itemDataType;
        this.spanSize = spanSize;
        this.id = id;
        this.text = text;
        this.imageUrl = imageUrl;
        this.bannerImages = bannerImages;
    }

    public void setItemDataType(int itemDataType) {
        this.itemDataType = itemDataType;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setBannerImages(ArrayList<String> bannerImages) {
        this.bannerImages = bannerImages;
    }

    public int getItemDataType() {
        return itemDataType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getBannerImages() {
        return bannerImages;
    }

    @Override
    public int getItemType() {
        return itemDataType;
    }
}
