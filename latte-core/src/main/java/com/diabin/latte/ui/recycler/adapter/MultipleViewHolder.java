package com.diabin.latte.ui.recycler.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by yangwenmin on 2017/10/28.
 */

public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }

}
