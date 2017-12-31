package com.diabin.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.delegates.bottom.BottomItemDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.util.log.LogUtil;

/**
 * 发现Fragment
 * Created by yangwenmin on 2017/10/24.
 */

public class DiscoverDelegate extends BottomItemDelegate {

    public final String TAG = "DiscoverDelegate";
    // 
    @Override
    public Object setLayout() {
        LogUtil.e(TAG,"setLayout");
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        LogUtil.e(TAG,"onBindView");
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        LogUtil.e(TAG,"onLazyInitView");
        super.onLazyInitView(savedInstanceState);

    }
}
