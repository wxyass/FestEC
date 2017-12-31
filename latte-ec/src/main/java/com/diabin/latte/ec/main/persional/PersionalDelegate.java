package com.diabin.latte.ec.main.persional;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.diabin.latte.delegates.bottom.BottomItemDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;
import com.diabin.latte.ec.main.persional.address.AddressDelegate;
import com.diabin.latte.ec.main.persional.list.ListAdapter;
import com.diabin.latte.ec.main.persional.list.ListBean;
import com.diabin.latte.ec.main.persional.list.ListItemType;
import com.diabin.latte.ec.main.persional.profile.UserProfileDelegate;
import com.diabin.latte.ec.main.persional.settings.SettingsDelegate;
import com.diabin.latte.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的Fragment
 * Created by yangwenmin on 2017/10/24.
 */

public class PersionalDelegate extends BottomItemDelegate {

    private final String TAG = "PersionalDelegate";

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    //
    @Override
    public Object setLayout() {
        LogUtil.e(TAG,"setLayout");
        return R.layout.delegate_persional;
    }

    @OnClick(R2.id.img_user_avatar)
    void onclickAvatar(){
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        LogUtil.e(TAG,"onBindView");
        // 初始化2个条目
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate()) // 将Fragment设置进去
                .setText("收货地址")
                .build();
        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingsDelegate()) // 将Fragment设置进去
                .setText("系统设置")
                .build();

        final List<ListBean> list = new ArrayList<>();
        list.add(address);
        list.add(system);

        // 设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(list);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));

    }
}