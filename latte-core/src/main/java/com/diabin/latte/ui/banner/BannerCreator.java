package com.diabin.latte.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.diabin.latte.R;

import java.util.ArrayList;

/**
 * Created by yangwenmin on 2017/10/28.
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner, ArrayList<String> banners, OnItemClickListener clickListener) {

        convenientBanner.setPages(new HolderCreator(), banners)// 数据结构,数据
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})// 黑白点
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)// 设置横向居中
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())//
                .startTurning(3000)// 延时滚动时间
                .setCanLoop(true);// 可以循环

    }
}
