package com.fzc.lowcopyjiantalk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.utils.StaticUtil;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.fragment
 * 文件名：MovieFragmentTwo
 * 创建者：fzc
 * 创建日期：2018/5/23 14:34
 * 描述
 */

public class MovieFragmentTwo extends Fragment {

    private Movie mMovie;

    private int movie_refresh_time;

    private int movie_position;

    private TextView tv_director_name;

    private ImageView iv_director_img;

    private String name;

    private String imgUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_two, null);

        tv_director_name = view.findViewById(R.id.tv_director_name);

        iv_director_img = view.findViewById(R.id.iv_director_img);

        initNumber();

        loadData();

        setWidgetView();

        return view;
    }

    private void setWidgetView() {

        tv_director_name.setText(name);

        Glide.with(getActivity()).load(imgUrl).centerCrop().into(iv_director_img);

    }

    private void initNumber() {

        movie_position = getActivity().getIntent().getIntExtra("time", 0);

        movie_refresh_time = StaticUtil.MOVIES_REFRESH_TIME;

    }

    private void loadData() {

        mMovie = (Movie) getActivity().getIntent().getSerializableExtra("movieBody");


        if (movie_refresh_time == 0) {

            name = mMovie.getSubjects().get(movie_position).getDirectors().get(0).getName();

            imgUrl = mMovie.getSubjects().get(movie_position).getDirectors().get(0).getAvatars().getMedium();

        } else {

            name = mMovie.getSubjects().get(movie_refresh_time * 20 + movie_position).getDirectors().get(0).getName();

            imgUrl = mMovie.getSubjects().get(movie_refresh_time * 20 + movie_position).getDirectors().get(0).getAvatars().getMedium();

        }

    }
}
