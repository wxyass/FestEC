package com.diabin.latte.ec.main.persional.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.util.log.LogUtil;

/**
 * 地址设置界面
 * Created by yangwenmin on 2017/11/1.
 */

public class AddressDelegate extends LatteDelegate {

    public final String TAG = "AddressDelegate";


    @Override
    public Object setLayout() {
        LogUtil.e(TAG,"setLayout");
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        LogUtil.e(TAG,"onBindView");
    }
}
