package com.diabin.latte.delegates;

/**
 * 以后正式使用的Fragment
 *
 * Created by yangwenmin on 2017/10/15.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate{

    public <T extends LatteDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
