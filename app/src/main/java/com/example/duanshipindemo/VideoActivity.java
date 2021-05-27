package com.example.duanshipindemo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.duanshipindemo.ijkplayer.VideoPlayerIJK;

public class VideoActivity extends Activity {

    private VideoPlayerIJK videoPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_video);
        videoPlayer = findViewById(R.id.videoPlayer);






    }
}
