package com.example.duanshipindemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.duanshipindemo.ijkplayer.VideoPlayerIJK;
import com.example.duanshipindemo.ijkplayer.VideoPlayerListener;
import com.example.duanshipindemo.ijkplayer.json.VideoModel;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoActivity extends Activity {

    private List<String> urls = new ArrayList<>();
    private VideoPlayerIJK videoPlayer;
    private Button bt1;
    private Button bt2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_video);
        videoPlayer = findViewById(R.id.videoPlayer);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                videoPlayer.setVideoPath(urls.get(0));
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                videoPlayer.setVideoPath(urls.get(1));
            }
        });

        initVideo();
    }

    private void initVideo() {
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
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


        urls.add("http://50069.gzc.svp.tencent-cloud.com/gzc_1000035_0bc3o4aj6aaabaabsad2svqjk56dt53qbh2a.f0.mp4");
        urls.add("http://50069.gzc.svp.tencent-cloud.com/gzc_1000035_0b53quazwaabaiad35vjwjqjjbodtocqdg2a.f0.mp4");
//        videoPlayer.setVideoPath(urls.get(0));
    }
}
