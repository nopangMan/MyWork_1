package com.fzc.lowcopyjiantalk.pager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.pager
 * 文件名：SplashViewPager
 * 创建者：fzc
 * 创建日期：2018/5/10 8:15
 * 描述
 */

public class SplashViewPager extends ViewPager {

    private boolean mPagingEnabled = true;


    public SplashViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPagingEnabled = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mPagingEnabled)return false;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mPagingEnabled) return false;
        return super.onTouchEvent(ev);
    }

    public void setPagingEnabled(boolean enabled) {
        mPagingEnabled = enabled;
    }
}
