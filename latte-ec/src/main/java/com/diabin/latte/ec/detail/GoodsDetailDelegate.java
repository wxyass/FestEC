package com.diabin.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.util.log.LogUtil;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 具体商品详情页
 * Created by yangwenmin on 2017/10/30.
 */

public class GoodsDetailDelegate  extends LatteDelegate{

    private final String TAG = "GoodsDetailDelegate";

    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        LogUtil.e(TAG,"setLayout");
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        LogUtil.e(TAG,"onBindView");
    }

    // 该Fragment出现的动画 横向的进入
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        LogUtil.e(TAG,"onCreateFragmentAnimator");
        return new DefaultHorizontalAnimator();
    }
}
