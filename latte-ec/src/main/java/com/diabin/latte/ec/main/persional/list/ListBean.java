package com.diabin.latte.ec.main.persional.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.diabin.latte.delegates.LatteDelegate;

/**
 * 实体类: 我的RecycleView条目的实体类
 * 同时也是 个人信息RecycleView条目的实体类
 * Created by yangwenmin on 2017/10/31.
 */

public class ListBean  implements MultiItemEntity {

    private int mItemType = 0; // 对应条目类型
    private String mImageUrl = null; // 头像url
    private String mText = null;// 标题(比如:收货地址,系统设置)
    private String mValue = null; // 内容(比如: 具体的地址)
    private int mId = 0; // 条目区分标记
    private LatteDelegate mDelegate = null;//
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;


    public ListBean(int itemType, String mImageUrl, String mText, String mValue, int mId, LatteDelegate mDelegate, CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mItemType = itemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getText() {
        if(mText == null){
            return "";
        }
        return mText;
    }

    public String getValue() {
        if(mValue == null){
            return "";
        }
        return mValue;
    }

    public int getId() {
        return mId;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    // 建造者模式
    public static final class Builder{

        private int id = 0; // 条目区分标记
        private int itemType = 0; // 对应条目类型
        private String imageUrl = null; // 头像url
        private String text = null;// 标题(比如:收货地址,系统设置)
        private String value = null; // 内容(比如: 具体的地址)

        private LatteDelegate delegate = null;//
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public ListBean build(){
            return  new ListBean(itemType,imageUrl,text,value,id,delegate,onCheckedChangeListener);
        }
    }
}
