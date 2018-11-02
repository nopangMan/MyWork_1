package com.fzc.lowcopyjiantalk.service.presenter;

import android.content.Context;
import android.content.Intent;

import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.service.mamager.MovieManager;
import com.fzc.lowcopyjiantalk.service.views.MovieView;
import com.fzc.lowcopyjiantalk.service.views.View;
import com.fzc.lowcopyjiantalk.utils.LogUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.service.presenter
 * 文件名：MoviePresenter
 * 创建者：fzc
 * 创建日期：2018/5/26 15:14
 * 描述   接受数据
 */

public class MoviePresenter implements Presenter {

    private MovieManager mMovieManager;

    private Movie mMovie;

    private Context mContext;

    private MovieView mMovieView;

    private CompositeSubscription mSubscription;

    public MoviePresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        mMovieManager = new MovieManager(mContext);
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void onStop() {
        if (mSubscription.hasSubscriptions()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void attachView(View view) {
        mMovieView = (MovieView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getTopMovies(int start, int count) {
        mSubscription.add(mMovieManager.getTopMovies(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        if (mMovieView == null){
                            LogUtil.i("movieView == null");
                        }else {
                            if (mMovie != null) {
                                mMovieView.onSuccess(mMovie);
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mMovieView.onFailed(throwable.getMessage());
                    }

                    @Override
                    public void onNext(Movie movie) {
                        mMovie = movie;
                        LogUtil.i(movie.getTitle());
                    }
                }));


    }
}
