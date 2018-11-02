package com.fzc.lowcopyjiantalk.base;

import android.content.Context;
import android.view.View;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.base
 * 文件名：BasePager
 * 创建者：fzc
 * 创建日期：2018/5/9 15:37
 * 描述
 */

public abstract class BasePager{

    public final Context rootContext;
    public View rootView;

    //是否初始化了数据
    public boolean isInitData = false;
    //从子元素返回View
    public BasePager(Context mContext) {
        rootContext = mContext;
        rootView = initView();
    }

    /**
     * 子元素必须实现的初始化View方法
     *
     * @return
     */
    public abstract View initView();

    /**
     * 子元素必须实现的初始化Data方法
     *
     * @return
     */
    public abstract void initData();
}
