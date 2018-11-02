package com.fzc.lowcopyjiantalk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.TextView;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.fragment.VideoItemFragment;
import com.fzc.lowcopyjiantalk.indicator.CirclePageIndicator;
import com.fzc.lowcopyjiantalk.pager.SplashViewPager;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.activity
 * 文件名：SplashActivity
 * 创建者：fzc
 * 创建日期：2018/5/9 22:36
 * 描述
 */

public class SplashActivity extends FragmentActivity implements View.OnClickListener {

    private SplashViewPager mVpVideo;
    private TextView mTvEnter;
    private CirclePageIndicator mViewPagerIndicator;
    private boolean mVisible;
    private ViewPagerAdapter mVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVisible = true;

        mVpVideo = findViewById(R.id.vp_video);
        mVpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mVpVideo.setAdapter(mVpAdapter);
        mVpVideo.setOffscreenPageLimit(2);

        mTvEnter = findViewById(R.id.tv_enter);
        mTvEnter.setOnClickListener(this);

        mViewPagerIndicator = findViewById(R.id.view_pager_indicator);
        mViewPagerIndicator.setViewPager(mVpVideo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_enter:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        finish();
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final int[] videoRes;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.videoRes = new int[]{R.raw.splash_1, R.raw.splash_2};
        }

        @Override
        public Fragment getItem(int paramInt) {
            VideoItemFragment videoItemFragment = new VideoItemFragment();
            Bundle localBundle = new Bundle();
            localBundle.putInt("position", paramInt);
            localBundle.putInt("videoRes", this.videoRes[paramInt]);
            videoItemFragment.setArguments(localBundle);
            if (paramInt < getCount()) {
                return videoItemFragment;
            }
            throw new RuntimeException("Position out of range. Adapter has " + getCount() + " items");
        }

        @Override
        public int getCount() {
            return this.videoRes.length;
        }
    }

    public void next(int positon) {
        int i = this.mVpVideo.getCurrentItem();
        if (positon == i) {
            positon += 1;

            this.mVpVideo.setCurrentItem(positon, true);
        }
    }
}
