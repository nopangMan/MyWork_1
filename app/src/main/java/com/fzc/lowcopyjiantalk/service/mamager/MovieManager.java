package com.fzc.lowcopyjiantalk.service.mamager;

import android.content.Context;

import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.service.RetrofitHelper;
import com.fzc.lowcopyjiantalk.service.RetrofitService;

import rx.Observable;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.service.mamager
 * 文件名：MovieManager
 * 创建者：fzc
 * 创建日期：2018/5/26 14:57
 * 描述   请求top250数据
 */

public class MovieManager {

    private RetrofitService mRetrofitService;

    public MovieManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getService();
    }

    public Observable<Movie> getTopMovies(int start, int count) {
        return mRetrofitService.getMovies(start, count);
    }

}
