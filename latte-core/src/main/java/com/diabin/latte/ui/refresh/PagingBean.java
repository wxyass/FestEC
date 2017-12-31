package com.diabin.latte.ui.refresh;

/**
 * Created by yangwenmin on 2017/10/28.
 */

public final class PagingBean {
    // 当前是第几页
    private int mPageIndex = 0;

    // 总数据条数
    private int mTotal = 0;

    // 一页显示几条数据
    private int mPageSize = 0;

    // 当前已显示了几条数据
    private int mCurrentCount = 0;

    // 加载延迟
    private int mDelayed = 0;

    public PagingBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public PagingBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public PagingBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public PagingBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public PagingBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    public int getPageIndex() {
        return mPageIndex;
    }

    public int getTotal() {
        return mTotal;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public int getDelayed() {
        return mDelayed;
    }

    // 加载第一页时
    PagingBean addIndex() {
        mPageIndex++;
        return this;
    }
}
