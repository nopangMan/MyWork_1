package com.fzc.lowcopyjiantalk.application;

import android.app.Application;

import com.fzc.lowcopyjiantalk.utils.KeyUtil;

import cn.bmob.v3.Bmob;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.application
 * 文件名：MyApplication
 * 创建者：fzc
 * 创建日期：2018/5/9 21:14
 * 描述   整个程序的入口
 */

public class MyApplication extends Application{
    public static boolean DEBUG = true;
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, KeyUtil.BMOB_KEY);
    }
}
