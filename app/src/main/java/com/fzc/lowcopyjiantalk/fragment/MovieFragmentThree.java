package com.fzc.lowcopyjiantalk.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.utils.StaticUtil;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.fragment
 * 文件名：MovieFragmentThree
 * 创建者：fzc
 * 创建日期：2018/5/23 14:34
 * 描述
 */

public class MovieFragmentThree extends Fragment {

    private Movie mMovie;

    private int movie_refresh_time;

    private int movie_position;

    private String style;

    private StringBuilder sb = new StringBuilder();

    private TextView tv_movie_style;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_three, null);

        tv_movie_style = view.findViewById(R.id.tv_movie_style);

        initNumber();

        loadData();

        setWidgetView();

        return view;
    }

    private void setWidgetView() {

        tv_movie_style.setText(style);

    }

    private void loadData() {

        mMovie = (Movie) getActivity().getIntent().getSerializableExtra("movieBody");


        if (movie_refresh_time == 0) {

            for (int i = 0; i < mMovie.getSubjects().get(movie_position).getGenres().size(); i++) {
                sb.append(" " + mMovie.getSubjects().get(movie_position).getGenres().get(i));
            }
            style = sb.toString();

        } else {

            for (int i = 0; i < mMovie.getSubjects().get(movie_position+movie_refresh_time*20).getGenres().size(); i++) {
                sb.append(" " + mMovie.getSubjects().get(movie_position+movie_refresh_time*20).getGenres().get(i));
            }
            style = sb.toString();
        }


    }

    private void initNumber() {

        movie_position = getActivity().getIntent().getIntExtra("time", 0);

        movie_refresh_time = StaticUtil.MOVIES_REFRESH_TIME;

    }
}
