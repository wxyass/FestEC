package com.diabin.latte.ui.recycler.bean;

import com.diabin.latte.ui.recycler.MultipleFields;

import java.util.LinkedHashMap;

/**
 * Created by yangwenmin on 2017/10/28.
 */

public class MultipleEntityBuider {

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuider() {
        // 先清除之前的数据
        FIELDS.clear();
    }

    public final MultipleEntityBuider setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleEntityBuider setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuider setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }


}
