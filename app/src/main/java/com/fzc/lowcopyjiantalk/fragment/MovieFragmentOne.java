package com.fzc.lowcopyjiantalk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.utils.LogUtil;
import com.fzc.lowcopyjiantalk.utils.StaticUtil;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.fragment
 * 文件名：MovieFragmentOne
 * 创建者：fzc
 * 创建日期：2018/5/23 14:33
 * 描述
 */

public class MovieFragmentOne extends Fragment {

    private TextView tv_original_title;

    private TextView tv_movie_year;

    private TextView tv_movie_rating;

    private TextView tv_movie_collect_count;


    private Movie mMovie;

    private String original_name;

    private String year;

    private double rating;

    private int collect_count;

    private int movie_refresh_time;

    private int movie_position;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_one, null);

        tv_original_title = view.findViewById(R.id.tv_movie_origin_title);

        tv_movie_year = view.findViewById(R.id.tv_movie_year);

        tv_movie_rating = view.findViewById(R.id.tv_movie_rating);

        tv_movie_collect_count = view.findViewById(R.id.tv_movie_collect_count);

        initNumber();

        loadData();

        setWidgetText();

        LogUtil.i("DirectorName " + mMovie.getSubjects().get(movie_position).getDirectors().get(0).getName());

        return view;
    }

    private void initNumber() {

        movie_position = getActivity().getIntent().getIntExtra("time", 0);

        movie_refresh_time = StaticUtil.MOVIES_REFRESH_TIME;

    }

    private void loadData() {

        mMovie = (Movie) getActivity().getIntent().getSerializableExtra("movieBody");


        if (movie_refresh_time == 0) {
            original_name = mMovie.getSubjects().get(movie_position).getOriginal_title();

            year = mMovie.getSubjects().get(movie_position).getYear();

            rating =  mMovie.getSubjects().get(movie_position).getRating().getAverage();

            collect_count = mMovie.getSubjects().get(movie_position).getCollect_count();

        } else {
            if (movie_position == 0) {
                original_name = mMovie.getSubjects().get(movie_refresh_time * 20).getOriginal_title();

                year = mMovie.getSubjects().get(movie_refresh_time * 20).getYear();

                rating =  mMovie.getSubjects().get(movie_refresh_time * 20).getRating().getAverage();

                collect_count = mMovie.getSubjects().get(movie_refresh_time * 20).getCollect_count();

            } else {
                original_name = mMovie.getSubjects().get(movie_position + movie_refresh_time * 20).getOriginal_title();

                year = mMovie.getSubjects().get(movie_position + movie_refresh_time * 20).getYear();

                rating =  mMovie.getSubjects().get(movie_position + movie_refresh_time * 20).getRating().getAverage();

                collect_count = mMovie.getSubjects().get(movie_position + movie_refresh_time * 20).getCollect_count();


            }

        }

    }

    private void setWidgetText() {

        LogUtil.i("position :" + movie_position + "\n" + "time  :" + movie_refresh_time);


        tv_original_title.setText(original_name);

        tv_movie_year.setText(year);

        tv_movie_rating.setText(rating+"");

        tv_movie_collect_count.setText(collect_count+"");


    }


}
