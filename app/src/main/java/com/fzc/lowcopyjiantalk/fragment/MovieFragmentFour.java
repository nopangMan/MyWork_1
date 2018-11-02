package com.fzc.lowcopyjiantalk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.adapter.MovieCastListAdapter;
import com.fzc.lowcopyjiantalk.entity.MovieCast;
import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.utils.StaticUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.fragment
 * 文件名：MovieFragmentFour
 * 创建者：fzc
 * 创建日期：2018/5/23 14:34
 * 描述
 */

public class MovieFragmentFour extends Fragment {


    private Movie mMovie;

    private int movie_refresh_time;

    private int movie_position;

    private List<MovieCast> casts = new ArrayList<>();

    private ListView lv_movie_cast;

    private MovieCastListAdapter mAdapter;


    private boolean hasLoad = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_movie_four, null);

        lv_movie_cast = view.findViewById(R.id.lv_movie_cast);

        initNumber();


        if (!hasLoad){
            loadData();
        }


        mAdapter = new MovieCastListAdapter(getActivity(), casts);


        lv_movie_cast.setAdapter(mAdapter);


        hasLoad = true;

        return view;
    }

    private void loadData() {

        mMovie = (Movie) getActivity().getIntent().getSerializableExtra("movieBody");


        if (movie_refresh_time == 0) {
            for (int i = 0; i < mMovie.getSubjects().get(movie_position).getCasts().size(); i++) {
                casts.add(new MovieCast(mMovie.getSubjects().get(movie_position).getCasts().get(i).getName(),
                        mMovie.getSubjects().get(movie_position).getCasts().get(i).getAvatars().getSmall()));
            }
        } else {
            for (int i = 0; i < mMovie.getSubjects().get(movie_position + movie_refresh_time * 20).getCasts().size(); i++) {
                casts.add(new MovieCast(mMovie.getSubjects().get(movie_position + movie_refresh_time * 20).getCasts().get(i).getName(),
                        mMovie.getSubjects().get(movie_position + movie_refresh_time * 20).getCasts().get(i).getAvatars().getSmall()));
            }
        }

    }

    private void initNumber() {

        movie_position = getActivity().getIntent().getIntExtra("time", 0);

        movie_refresh_time = StaticUtil.MOVIES_REFRESH_TIME;

    }
}
