package com.diabin.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;

import com.diabin.latte.R;
import com.diabin.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 *
 * 仅仅是个容器,继承的me.yokeyword.fragmentation.SupportActivity
 *
 * 需要主Activity继承它
 *
 * Created by yangwenmin on 2017/10/14.
 */

public abstract class ProxyActivity extends AppCompatActivity implements ISupportActivity {

    private final SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);

    // 子类传入根LatteDelegate(即:传入根Fragment)
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        // 初始化视图
        initContainer(savedInstanceState);
    }

    // 初始化视图
    private void initContainer(@Nullable Bundle savedInstanceState){
        // 根容器
        final ContentFrameLayout container = new ContentFrameLayout(this);
        // 需要传入一个id(这个id需要先在资源文件中定义,再引用,如下)
        container.setId(R.id.delegate_container);
        // 将容器绑定到窗口上
        setContentView(container);
        // 在容器中加载根Fragment
        if (savedInstanceState==null){
            // loadRootFragment()是SupportActivity独有的一个方法,
            // 用于加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
            DELEGATE.loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    // 优化: 垃圾回收
    @Override
    protected void onDestroy() {
        DELEGATE.onDestroy();
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public void onBackPressedSupport() {
        DELEGATE.onBackPressedSupport();
    }

    @Override
    public void onBackPressed() {
        DELEGATE.onBackPressed();
    }
}
