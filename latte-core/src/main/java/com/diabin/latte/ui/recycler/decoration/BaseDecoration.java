package com.diabin.latte.ui.recycler.decoration;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * 继承第三方库的DividerItemDecoration
 *
 * 分割线
 *
 * Created by yangwenmin on 2017/10/29.
 */

public class BaseDecoration extends DividerItemDecoration {

    // 线的颜色,粗细
    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    // 简单工厂模式 创建对象
    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color,size);
    }


}
