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
import android.widget.Toast;

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
    private TextView tv_url2;
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
        tv_url2 = view.findViewById(R.id.tv_url2);
        videoPlayer = view.findViewById(R.id.videoPlayer);
        url = getArguments().getString(URL);
        page = getArguments().getString("page");
        tv_url.setText(url);
        tv_url2.setText(url);

        tv_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlayer.pause();
            }
        });

        tv_url2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlayer.reLoad();
            }
        });

        initVideo();
        return view;
    }

    private void initVideo() {
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("IjkMediaPlayer", "我崩溃了么");
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
                if (!isVisibleToUser) {
                    videoPlayer.pause();
                }
            }

            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {

            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

            }
        });
        if (isVisibleToUser) {
            videoPlayer.setVideoPath(url, true);
        } else {
            videoPlayer.setVideoPath(url, false);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            if (videoPlayer != null) {
                videoPlayer.setPlay(true);
                videoPlayer.reLoad();
            }
        } else {
            if (videoPlayer != null) {
                videoPlayer.setPlay(false);
                videoPlayer.pause();
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
        try {
            IjkMediaPlayer.native_profileEnd();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("IjkMediaPlayer", "我崩溃了么");
        }

        if (videoPlayer != null) {
            videoPlayer.stop();
            videoPlayer.release();
        }
    }
}


