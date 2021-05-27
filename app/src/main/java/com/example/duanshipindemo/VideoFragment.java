package com.example.duanshipindemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.duanshipindemo.ijkplayer.VideoPlayerIJK;
import com.example.duanshipindemo.ijkplayer.VideoPlayerListener;

import java.time.Year;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoFragment extends Fragment {
    private TextView tv_url;
    private VideoPlayerIJK videoPlayer;

    private DrawerLayout dlBackPlay;
    private String url;

    public static final String URL = "URL";
    private String page;

    boolean isVisibleToUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_video, null);
        tv_url = view.findViewById(R.id.tv_url);
        videoPlayer = view.findViewById(R.id.videoPlayer);

        url = getArguments().getString(URL);
        page = getArguments().getString("page");
        tv_url.setText(url);

        initVideo();

        return view;
    }

    private void initVideo() {
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            e.printStackTrace();
        }


        videoPlayer.setListener(new VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {

            }

            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {

            }

            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {

            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

            }
        });
        if (isVisibleToUser) {
            videoPlayer.setVideoPath(url);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            if (videoPlayer != null) {
                videoPlayer.setVideoPath(url);
            }
        } else {
            if (videoPlayer != null) {
                videoPlayer.stop();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isVisibleToUser) {
            Log.e("onResumePlay  ", page);
        } else {
            Log.e("onResumefinish  ", page);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isVisibleToUser) {
            Log.e("onPausePlay  ", page);
        } else {
            Log.e("onPausefinish  ", page);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isVisibleToUser) {
            Log.e("onDestroyPlay  ", page);
        } else {
            Log.e("onDestroyfinish  ", page);
        }
    }
}


