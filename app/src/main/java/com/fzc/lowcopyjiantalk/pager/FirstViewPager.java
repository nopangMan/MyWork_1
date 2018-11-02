package com.fzc.lowcopyjiantalk.pager;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.base.BasePager;
import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.service.presenter.MoviePresenter;
import com.fzc.lowcopyjiantalk.service.views.MovieView;
import com.fzc.lowcopyjiantalk.utils.LogUtil;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.pager
 * 文件名：FirstViewtPager
 * 创建者：fzc
 * 创建日期：2018/5/9 17:47
 * 描述   首页页面
 */

public class FirstViewPager extends BasePager {
    private MoviePresenter mMoviePresenter;
    private MovieView mMovieView;
    private Button btn_send_request;
    private TextView tv_response_show;

    public FirstViewPager(Context mContext) {
        super(mContext);
    }

    @Override
    public View initView() {

        View view = View.inflate(rootContext, R.layout.fragment_first, null);

        btn_send_request = view.findViewById(R.id.btn_send_request);

        tv_response_show = view.findViewById(R.id.tv_response_show);

        return view;
    }

    @Override
    public void initData() {

        btn_send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoviePresenter.getTopMovies(0, 1);
            }
        });

        mMoviePresenter = new MoviePresenter(rootContext);

        mMoviePresenter.onCreate();


        mMovieView = new MovieView() {
            @Override
            public void onSuccess(Movie movie) {
                tv_response_show.setText(movie.getTitle());
            }

            @Override
            public void onFailed(String message) {
                LogUtil.i("!!!!!!!   " + message);
            }
        };


        mMoviePresenter.attachView(mMovieView);


    }

    @Override
    protected void finalize() throws Throwable {
        mMoviePresenter.onStop();
        super.finalize();
    }
}
