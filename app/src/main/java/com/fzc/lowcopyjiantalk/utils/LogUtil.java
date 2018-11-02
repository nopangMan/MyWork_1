package com.fzc.lowcopyjiantalk.utils;

import android.util.Log;

import com.fzc.lowcopyjiantalk.application.MyApplication;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.utils
 * 文件名：LogUtil
 * 创建者：fzc
 * 创建日期：2018/5/9 21:27
 * 描述
 */

public class LogUtil {

    private LogUtil() {
        throw new UnsupportedOperationException("不能被实例化");
    }

    //TAG
    public static final String TAG = "LowCopyJianTalk";

    //五个等级  DIWE

    public static void d(String text) {
        if (MyApplication.DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void i(String text) {
        if (MyApplication.DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void w(String text) {
        if (MyApplication.DEBUG) {
            Log.w(TAG, text);
        }
    }

    public static void e(String text) {
        if (MyApplication.DEBUG) {
            Log.e(TAG, text);
        }
    }
}
