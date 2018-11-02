package com.fzc.lowcopyjiantalk.pager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.adapter.FindRecyclerViewAdapter;
import com.fzc.lowcopyjiantalk.base.BasePager;
import com.fzc.lowcopyjiantalk.entity.LoadBody;
import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.service.RetrofitService;
import com.fzc.lowcopyjiantalk.utils.StaticUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.pager
 * 文件名：FindViewPager
 * 创建者：fzc
 * 创建日期：2018/5/9 17:48
 * 描述   发现页面
 */

public class FindViewPager extends BasePager {

    private static final String TAG = "FindViewPager";

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mRefreshLayout;

    private GridLayoutManager mGridLayoutManager;

    //    private List<Fruit> mFruits = new ArrayList<>();

    private View view;

    private Toolbar mToolbar;

    private FindRecyclerViewAdapter mAdapter;

    private List<LoadBody> mLoadBodies = new ArrayList<>();

    //    private Fruit[] fruits;
    private Boolean isFirst = true;

    private Movie mMovie;

    public FindViewPager(Context mContext) {
        super(mContext);

        firstLoadData();
    }

    @Override
    public View initView() {

        view = View.inflate(rootContext, R.layout.fragment_find, null);

        mToolbar = view.findViewById(R.id.tool_bar);

        mRecyclerView = view.findViewById(R.id.find_recycler_view);

        mRefreshLayout = view.findViewById(R.id.find_swipe_refresh);

        mRefreshLayout.setColorSchemeColors(Color.GREEN);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ++StaticUtil.MOVIES_REFRESH_TIME;

                if (StaticUtil.MOVIES_REFRESH_TIME == 5) {
                    StaticUtil.MOVIES_REFRESH_TIME = 0;
                }

                setLoadBodies();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                mRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();


            }
        });
        return view;
    }

    @Override
    public void initData() {


    }

    private void firstLoadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(StaticUtil.BASE_MOVIE_URL)
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Observable<Movie> observable = service.getMovies(0, 100);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                               @Override
                               public void onCompleted() {

                                   StaticUtil.MOVIES_COUNT = StaticUtil.MOVIES_COUNT + 20;

                                   mGridLayoutManager = new GridLayoutManager(rootContext, 2);

                                   mRecyclerView.setLayoutManager(mGridLayoutManager);

                                   mAdapter = new FindRecyclerViewAdapter(rootContext, mLoadBodies, mMovie);

                                   mRecyclerView.setAdapter(mAdapter);

                               }

                               @Override
                               public void onError(Throwable throwable) {

                               }

                               @Override
                               public void onNext(Movie movie) {
                                   mMovie = movie;
                                   for (int i = 0; i < 20; i++) {
                                       mLoadBodies.add(new LoadBody(movie.getSubjects().get(i).getTitle(),
                                               movie.getSubjects().get(i).getImages().getSmall()));
                                   }
                               }
                           }
                );

    }

    private void setLoadBodies() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(StaticUtil.BASE_MOVIE_URL)
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Observable<Movie> observable = service.getMovies(0, 100);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                               @Override
                               public void onCompleted() {
                                   StaticUtil.MOVIES_COUNT = StaticUtil.MOVIES_COUNT + 20;
                                   if (StaticUtil.MOVIES_COUNT == 100) {
                                       StaticUtil.MOVIES_COUNT = 0;
                                   }
                                   Toast.makeText(rootContext, "刷新完成了 = = 老哥~", Toast.LENGTH_SHORT).show();
                               }

                               @Override
                               public void onError(Throwable throwable) {

                               }

                               @Override
                               public void onNext(Movie movie) {
                                   for (int i = 0; i < 20; i++) {
                                       mLoadBodies.set(i, new LoadBody(movie.getSubjects().get(i + StaticUtil.MOVIES_COUNT).getTitle(),
                                               movie.getSubjects().get(i + StaticUtil.MOVIES_COUNT).getImages().getSmall()));
                                       mAdapter.notifyDataSetChanged();
                                   }
                               }
                           }
                );
    }
}

