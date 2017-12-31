package com.diabin.latte.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.diabin.latte.ui.recycler.convert.DataConverter;
import com.diabin.latte.ui.recycler.ItemType;
import com.diabin.latte.ui.recycler.MultipleFields;
import com.diabin.latte.ui.recycler.bean.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 首页数据转换类
 * Created by yangwenmin on 2017/10/28.
 */

public class IndexDataConverter extends DataConverter {

    // 在这里实现数据转换,即将json转成条目所需要的bean
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        // 将返回的json数据 解析成data数组
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            // 获取每个条目中 每个字段的值
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();

            int type = 0;
            if (imageUrl == null && text != null) {// 只有文字
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {// 只有图片
                type = ItemType.IMAGE;
            } else if (imageUrl != null) { // 图文并茂
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {// 轮播图
                type = ItemType.BANNER;
                // Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            // 保存每个条目数据 组成bean对象
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
