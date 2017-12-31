package com.diabin.latte.ui.launcher.bannerholder;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by yangwenmin on 2017/10/21.
 */

public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mImageView = null;

    // 条目布局
    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    // 设置图片
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);

    }
}
