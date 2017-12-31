package com.diabin.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.delegates.bottom.BottomItemDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.util.log.LogUtil;

/**
 * 购物车Fragment
 * Created by yangwenmin on 2017/10/24.
 */

public class ShopCartDelegate extends BottomItemDelegate {

    private final String TAG = "ShopCartDelegate";
    //
    @Override
    public Object setLayout() {
        LogUtil.e(TAG,"setLayout");
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        LogUtil.e(TAG,"onBindView");
    }
}