package com.fzc.lowcopyjiantalk.service;

import android.content.Context;

import com.fzc.lowcopyjiantalk.utils.StaticUtil;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.service
 * 文件名：RetrofitHelpter
 * 创建者：fzc
 * 创建日期：2018/5/26 14:33
 * 描述   网络请求准备
 */

public class RetrofitHelper {

    private Context mContext;

    private Retrofit mRetrofit;

    private OkHttpClient client = new OkHttpClient();

    private GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());

    private static RetrofitHelper instance = null;

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitHelper(context);
        }

        return instance;
    }

    private RetrofitHelper(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(StaticUtil.BASE_MOVIE_URL)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public RetrofitService getService() {
        return mRetrofit.create(RetrofitService.class);
    }
}
