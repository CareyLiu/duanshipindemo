package com.example.duanshipindemo.ijkplayer;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoPlayerIJK extends FrameLayout {
    /**
     * 由ijkplayer提供，用于播放视频，需要给他传入一个surfaceView
     */
    private IMediaPlayer mMediaPlayer = null;

    /**
     * 视频文件地址
     */
    private String mPath = "";

    private SurfaceView surfaceView;

    private VideoPlayerListener listener;
    private Context mContext;

    private boolean isLord = false;
    private boolean isPlay = false;

    public VideoPlayerIJK(@NonNull Context context) {
        super(context);
        initVideoView(context);
    }

    public VideoPlayerIJK(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initVideoView(context);
    }

    public VideoPlayerIJK(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVideoView(context);
    }

    private void initVideoView(Context context) {
        mContext = context;

        //获取焦点，不知道有没有必要~。~
        setFocusable(true);
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    /**
     * 设置视频地址。
     * 根据是否第一次播放视频，做不同的操作。
     */
    public void setVideoPath(String path, boolean isPlay) {
        this.isPlay = isPlay;
        if (TextUtils.equals("", mPath)) {
            //如果是第一次播放视频，那就创建一个新的surfaceView
            mPath = path;
            createSurfaceView();
            Log.e("我是第一次执行的啊啊啊", "");
        } else {
            //否则就直接load
            mPath = path;
            load();
            Log.e("我是重新加载执行的啊啊啊", "");
        }
    }

    /**
     * 新建一个surfaceview
     */
    private void createSurfaceView() {
        //生成一个新的surface view
        surfaceView = new SurfaceView(mContext);
        surfaceView.getHolder().addCallback(new LmnSurfaceCallback());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);
        surfaceView.setLayoutParams(layoutParams);
        this.addView(surfaceView);
    }

    /**
     * surfaceView的监听器
     */
    private class LmnSurfaceCallback implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            if (!isLord) {
                load();
            } else {
                reLoad();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
            }
        }
    }

    /**
     * 加载视频
     */
    public void load() {
        //每次都要重新创建IMediaPlayer
        isLord = true;
        createPlayer();
        try {
            mMediaPlayer.setDataSource(mPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //给mediaPlayer设置视图
        mMediaPlayer.setDisplay(surfaceView.getHolder());
        mMediaPlayer.prepareAsync();
        if (!isPlay) {
//            mMediaPlayer.stop();
        }
    }

    /**
     * 加载视频
     */
    public void reLoad() {
        isLord = true;
        //给mediaPlayer设置视图
        if (mMediaPlayer != null) {
            mMediaPlayer.setDisplay(surfaceView.getHolder());
            mMediaPlayer.start();
        }
    }

    /**
     * 创建一个新的player
     */
    private void createPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.setDisplay(null);
            mMediaPlayer.release();
        }
        IjkMediaPlayer ijkMediaPlayer = new IjkMediaPlayer();
//        ijkMediaPlayer.native_setLogLevel(IjkMediaPlayer.IJK_LOG_DEBUG);
        ijkMediaPlayer.setLogEnabled(false);
        mMediaPlayer = ijkMediaPlayer;
        if (listener != null) {
            mMediaPlayer.setOnPreparedListener(listener);
            mMediaPlayer.setOnInfoListener(listener);
            mMediaPlayer.setOnSeekCompleteListener(listener);
            mMediaPlayer.setOnBufferingUpdateListener(listener);
            mMediaPlayer.setOnErrorListener(listener);
            mMediaPlayer.setOnCompletionListener(listener);
        }
    }


    public void setListener(VideoPlayerListener listener) {
        this.listener = listener;
        if (mMediaPlayer != null) {
            mMediaPlayer.setOnPreparedListener(listener);
        }
    }

    /**
     * 下面封装了一下控制视频的方法
     */
    public void start() {
        if (mMediaPlayer != null) {
            Log.e("mMediaPlayer", "start");
            reLoad();
        }
    }

    public void release() {
        if (mMediaPlayer != null) {
            Log.e("mMediaPlayer", "release");
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void pause() {
        if (mMediaPlayer != null) {
            Log.e("mMediaPlayer", "pause");
            mMediaPlayer.pause();
        }
    }

    public void stop() {
        if (mMediaPlayer != null) {
            Log.e("mMediaPlayer", "stop");
            mMediaPlayer.stop();
        }
    }


    public void reset() {
        if (mMediaPlayer != null) {
            Log.e("mMediaPlayer", "reset");
            mMediaPlayer.reset();
        }
    }


    public long getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        } else {
            return 0;
        }
    }


    public long getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }


    public void seekTo(long l) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(l);
        }
    }

    public boolean isPlaying() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        } else {
            return false;
        }
    }
}
