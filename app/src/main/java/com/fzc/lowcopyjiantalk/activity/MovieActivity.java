package com.fzc.lowcopyjiantalk.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.adapter.MovieFragmentAdapter;
import com.fzc.lowcopyjiantalk.fragment.MovieFragmentFour;
import com.fzc.lowcopyjiantalk.fragment.MovieFragmentOne;
import com.fzc.lowcopyjiantalk.fragment.MovieFragmentThree;
import com.fzc.lowcopyjiantalk.fragment.MovieFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.activity
 * 文件名：MovieActivity
 * 创建者：fzc
 * 创建日期：2018/5/22 21:49
 * 描述
 */

public class MovieActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private CollapsingToolbarLayout collapsing_toolbar;

    private ImageView iv_fruit_img;

    private TextView tv_fruit_content;

    private String name;

    private String imgUrl;

    private List<Fragment> mFragments;

    private TabLayout tab_layout_movie;

    private ViewPager view_pager_movie;

    private MovieFragmentAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        getData();

        initData();

        initView();

        setContent();

    }

    private void initData() {
        mFragments = new ArrayList<>();

        mFragments.add(new MovieFragmentOne());
        mFragments.add(new MovieFragmentTwo());
        mFragments.add(new MovieFragmentThree());
        mFragments.add(new MovieFragmentFour());


        mAdapter = new MovieFragmentAdapter(getSupportFragmentManager(), mFragments);

    }

    private void setContent() {
        view_pager_movie.setAdapter(mAdapter);

        tab_layout_movie.setupWithViewPager(view_pager_movie);


        collapsing_toolbar.setCollapsedTitleTextColor(Color.GREEN);
        collapsing_toolbar.setExpandedTitleColor(Color.YELLOW);
        collapsing_toolbar.setTitle(name);


        Glide.with(this).load(imgUrl).into(iv_fruit_img);
    }

    private void getData() {
        name = getIntent().getStringExtra("name");

        imgUrl = getIntent().getStringExtra("imgUrl");
    }

    private void initView() {

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        iv_fruit_img = (ImageView) findViewById(R.id.iv_fruit_img);


        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        tab_layout_movie = (TabLayout) findViewById(R.id.tab_layout_movie);

        view_pager_movie = (ViewPager) findViewById(R.id.view_pager_movie);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
