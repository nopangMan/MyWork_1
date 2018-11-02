package com.fzc.lowcopyjiantalk.service.presenter;

import android.content.Intent;

import com.fzc.lowcopyjiantalk.service.views.View;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.service.presenter
 * 文件名：Presenter
 * 创建者：fzc
 * 创建日期：2018/5/26 15:08
 * 描述
 */

public interface Presenter {

    void onCreate();

    void onStart();

    void pause();//暂时没用到

    void onStop();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);//暂时没用到
}
