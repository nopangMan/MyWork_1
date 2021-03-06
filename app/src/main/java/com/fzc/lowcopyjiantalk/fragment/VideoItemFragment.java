package com.fzc.lowcopyjiantalk.fragment;

import android.media.MediaPlayer;
import android.support.v4.app.FragmentActivity;

import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.activity.SplashActivity;
import com.fzc.lowcopyjiantalk.view.FullScreenVideoView;

/**
 * Function:
 * Created by dubin on 2016/12/26.
 */

public class VideoItemFragment extends LazyLoadFragment
        implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

    private boolean mHasPaused;
    private int mVideoPosition;
    private int position;
    private int videoRes;
    private FullScreenVideoView mVideoView;


    public VideoItemFragment() {
        mHasPaused = false;
        mVideoPosition = 0;
    }

    @Override
    protected int setContentView() {
        return R.layout.video_viewpager_item;
    }

    @Override
    protected void lazyLoad() {
        if (getArguments() == null) {
            return;
        }
        position = getArguments().getInt("position");
        videoRes = getArguments().getInt("videoRes");

        mVideoView = findViewById(R.id.vvSplash);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnPreparedListener(this);
        mVideoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + videoRes);


    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        FragmentActivity localFragmentActivity = getActivity();
        if ((localFragmentActivity != null) && ((localFragmentActivity instanceof SplashActivity))) {
            ((SplashActivity) localFragmentActivity).next(position);
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        FragmentActivity localFragmentActivity = getActivity();
        if ((localFragmentActivity != null) && ((localFragmentActivity instanceof SplashActivity))) {
            ((SplashActivity) localFragmentActivity).next(position);
        }
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
            if (mVideoView != null) {
                mVideoView.requestFocus();
                mVideoView.setOnCompletionListener(this);
                mVideoView.seekTo(0);
                mVideoView.start();
            }
            return;

    }


    @Override
    public void onResume() {
        super.onResume();
        if (mHasPaused) {
            if (mVideoView != null) {
                mVideoView.seekTo(mVideoPosition);
                mVideoView.resume();
            }
        }
        return;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoPosition = mVideoView.getCurrentPosition();
        }
        mHasPaused = true;
    }

    public void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
        return;
    }

    public void pause() {
        if ((mVideoView != null) && (mVideoView.canPause())) {
            mVideoView.setOnCompletionListener(null);
            mVideoView.pause();
        }
        return;

    }

    public void play() {
        if (mVideoView != null) {
            mVideoView.requestFocus();
            mVideoView.setOnCompletionListener(this);
            mVideoView.seekTo(0);
        } else {
            return;
        }
        mVideoView.start();
    }

    public void reLoadVideo() {
        if (mVideoView != null) {
            mVideoView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + videoRes);
        }
    }

}
