package com.diabin.latte.ui.recycler.decoration;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by yangwenmin on 2017/10/29.
 */

public class DividerLookupImpl implements DividerItemDecoration.DividerLookup{

    private final int COLOR;// 分割线颜色
    private final int SIZE;// 分割线粗细

    public DividerLookupImpl(int color, int size) {
        this.COLOR = color;
        this.SIZE = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder()
                .size(SIZE)
                .color(COLOR)
                .build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder()
                .size(SIZE)
                .color(COLOR)
                .build();
    }
}
