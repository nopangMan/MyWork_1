package com.fzc.lowcopyjiantalk.service;

import com.fzc.lowcopyjiantalk.service.Bean.Movie;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.service
 * 文件名：RetrofitService
 * 创建者：fzc
 * 创建日期：2018/5/26 14:34
 * 描述   top250请求接口
 */

public interface RetrofitService {
    @GET("/v2/movie/top250")
    Observable<Movie> getMovies(@Query("start") int start,
                                @Query("count") int count);
}
