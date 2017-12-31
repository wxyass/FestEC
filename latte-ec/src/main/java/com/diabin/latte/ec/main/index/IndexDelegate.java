package com.diabin.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.diabin.latte.delegates.bottom.BottomItemDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.ec.R2;
import com.diabin.latte.ec.main.ECBottomDelegate;
import com.diabin.latte.net.NetUrl;
import com.diabin.latte.ui.recycler.decoration.BaseDecoration;
import com.diabin.latte.ui.refresh.RefreshHandle;
import com.diabin.latte.util.log.LogUtil;

import butterknife.BindView;

/**
 * 首页Fragment
 * Created by yangwenmin on 2017/10/24.
 */

public class IndexDelegate extends BottomItemDelegate {

    public final String TAG = "IndexDelegate";

    // 下拉刷新控件
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;

    // RecyclerView
    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;

    // 标题Toolbar
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;

    // 搜索
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    // 用来刷新的帮助类
    private RefreshHandle mRefreshHandle = null;

    //
    @Override
    public Object setLayout() {
        LogUtil.e(TAG,"setLayout");
        return R.layout.delegate_index;
    }

    //
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        LogUtil.e(TAG,"onBindView");
        // 初始化下拉刷新帮助类
        // mRefreshHandle = new RefreshHandle(mRefreshLayout);
        mRefreshHandle = RefreshHandle.create(mRefreshLayout,mRecyclerView,new IndexDataConverter());
        /*mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });*/

        /* // 测试 删除
        RestClient.builder()
                .url(NetUrl.INDEX_DATA)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);
                        final ArrayList<MultipleItemEntity> list = converter.convert();
                        final String imageurl = list.get(1).getField(MultipleFields.IMAGE_URL);
                        Toast.makeText(getContext(), imageurl, Toast.LENGTH_SHORT).show();
                    }
                })
                .builde()
                .get();*/
    }

    // 初始化下拉刷新控件的颜色
    private void initRefreshLayout() {
        // 设置下拉时球的颜色
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        // 设置位置和表现 true:下拉时球由小变大,回弹时球由大变小 120:起始高度 300:下降终止高度
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    // 初始化RecyclerView 父布局
    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
        // 条目之间添加分割线
        mRecyclerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.app_background),5));
        // 添加点击事件
        final ECBottomDelegate ecBottomDelegate = getParentDelegate();
        // 设置RecyclerView的条目点击事件
        mRecyclerView.addOnItemTouchListener(IndexItemClickListenr.create(ecBottomDelegate));
    }

    //
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        LogUtil.e(TAG,"onLazyInitView");

        // 初始化下拉刷新控件的颜色
        initRefreshLayout();

        // 初始化RecyclerView 父布局
        initRecyclerView();

        // 初始化首页数据
        mRefreshHandle.firstPage(NetUrl.INDEX_DATA);
    }
}
