package com.diabin.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diabin.latte.delegates.bottom.BottomItemDelegate;
import com.diabin.latte.ec.R;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.IError;
import com.diabin.latte.net.callback.IRequest;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.net.callback.OnDownLoadProgress;
import com.diabin.latte.util.file.SDCardUtil;
import com.diabin.latte.util.log.LogUtil;

/**
 * 分类Fragment
 * Created by yangwenmin on 2017/10/24.
 */

public class SortDelegate extends BottomItemDelegate {

    public final String TAG = "SortDelegate";

    //
    @Override
    public Object setLayout() {
        LogUtil.e(TAG,"setLayout");
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        LogUtil.e(TAG,"onBindView");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.e(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        downApk();
    }

    public void  downApk(){
        String url = "http://kqposition.hnxkqwy.com/Res/File/2017_11/ccb9f5d6_7a89_4658_8991_cce9ad088d3f.zip";
        RestClient.builder()
                .url(url)
                .dir(SDCardUtil.getSDCardPath() + "/a/")// 保存文件夹路径
                .extension("zip")                     // 文件扩展名
                .name("aaaa.zip")                 // 文件全名（会覆盖）
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        LogUtil.d(TAG, "onRequestStart: ");
                    }

                    @Override
                    public void onRequestEnd() {
                        LogUtil.d(TAG, "onRequestEnd: ");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        LogUtil.d(TAG, "onError: code= " + code + "  msg=" + msg);

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtil.d(TAG, "onSuccess: " + response);
                    }
                })
                .onDownLoadProgress(new OnDownLoadProgress() {
                    @Override
                    public void onProgressUpdate(long fileLength, int downLoadedLength) {
                        LogUtil.d(TAG, "onProgressUpdate: " + fileLength + "/" + downLoadedLength);

                    }
                })
                .builde()
                .download();
    }
}