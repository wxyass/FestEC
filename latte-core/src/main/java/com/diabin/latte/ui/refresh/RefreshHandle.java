package com.diabin.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.diabin.latte.app.Latte;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.ui.recycler.adapter.MultipleRecyclerAdapter;
import com.diabin.latte.ui.recycler.convert.DataConverter;

/**
 * 下拉刷新帮助类
 * Created by yangwenmin on 2017/10/27.
 */

public class RefreshHandle implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;


    // 构造方法
    private RefreshHandle(SwipeRefreshLayout swiperefreshlayout,
                          RecyclerView recyclerview,
                          DataConverter converter,
                          PagingBean bean) {
        this.REFRESH_LAYOUT = swiperefreshlayout;
        this.RECYCLERVIEW = recyclerview;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    // 简单工厂模式 创建RefreshHandle对象
    public static RefreshHandle create(SwipeRefreshLayout swiperefreshlayout,
                                       RecyclerView recyclerview,
                                       DataConverter converter) {
        return new RefreshHandle(swiperefreshlayout, recyclerview, converter, new PagingBean());
    }

    private void refresh() {
        // 开始加载动画
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 进行一些网络请求
                // ...

                // 关闭动画
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    // 一进入首页,立即加载第一页数据
    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        // 解析json
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));

                        // 适配器
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        // 加载更多
                        mAdapter.setOnLoadMoreListener(RefreshHandle.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();

                    }

                })
                .builde()
                .get();
    }

    @Override
    public void onRefresh() {

        refresh();
    }

    // 加载更多
    @Override
    public void onLoadMoreRequested() {

    }
}
