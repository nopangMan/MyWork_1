package com.fzc.lowcopyjiantalk.utils;

import android.content.Context;
import android.widget.Toast;

import com.fzc.lowcopyjiantalk.application.MyApplication;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.utils
 * 文件名：ToastUtil
 * 创建者：fzc
 * 创建日期：2018/5/9 21:48
 * 描述
 */

public class ToastUtil {

    //private控制不应该被实例化
    private ToastUtil() {
        throw new UnsupportedOperationException("不能被实例化");
    }

    public static void makeShortToast(Context context, String content) {
        if (MyApplication.DEBUG) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void makeLongToast(Context context, String content) {
        if (MyApplication.DEBUG) {
            Toast.makeText(context, content, Toast.LENGTH_LONG).show();
        }
    }
}
