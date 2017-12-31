package com.diabin.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.detail.GoodsDetailDelegate;

/**
 * Created by yangwenmin on 2017/10/30.
 */

public class IndexItemClickListenr extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public IndexItemClickListenr(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(LatteDelegate delegate){
        return new IndexItemClickListenr(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        // 跳转商品详情页
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create();
        //DELEGATE.start(delegate);
        DELEGATE.getSupportDelegate().start(delegate);

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
