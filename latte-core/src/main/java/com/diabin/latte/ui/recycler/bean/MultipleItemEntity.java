package com.diabin.latte.ui.recycler.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.diabin.latte.ui.recycler.MultipleFields;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * RecyclerView的每个条目的实体bean
 *
 * Created by yangwenmin on 2017/10/28.
 */

public class MultipleItemEntity implements MultiItemEntity {

    //
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();


    // 新建LinkedHashMap处理每个条目的数据,
    // 比如: 条目类型(ITEM_TYPE),标题(TITLE),图片url(IAMGE_URL)等等
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();



    // 使用SoftReference便于内存释放
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);




    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleEntityBuider builder(){
        return new MultipleEntityBuider();
    }

    // 控制RecyclerView的每一个条目的样式和表现特征
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    // 在MULTIPLE_FIELDS中存放每一个条目 需要存放的数据
    public final MultipleItemEntity setField(Objects key, Objects value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }

    // 在MULTIPLE_FIELDS中 根据key获取相应value
    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    // 获取整个MULTIPLE_FIELDS
    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERENCE.get();
    }


}
