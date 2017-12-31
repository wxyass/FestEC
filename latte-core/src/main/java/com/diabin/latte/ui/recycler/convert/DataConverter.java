package com.diabin.latte.ui.recycler.convert;

import com.diabin.latte.ui.recycler.bean.MultipleItemEntity;

import java.util.ArrayList;

/**
 *  数据转换处理
 * Created by yangwenmin on 2017/10/28.
 */

public abstract class DataConverter {

    protected  final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData = null;

    // 子类实现转换 并将转换后的ArrayList<MultipleItemEntity> 返回去
    public abstract ArrayList<MultipleItemEntity> convert();

    // 子类将要转换的json传进来
    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    //
    protected String getJsonData(){
        if(mJsonData == null||mJsonData.isEmpty()){
            throw new NullPointerException("json数据为空");
        }
        return mJsonData;
    }

    public void clearData(){
        ENTITIES.clear();
    }
}
