package com.example.duanshipindemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class VideoFragment extends Fragment {

    DrawerLayout dlBackPlay;
    private String url;
    public static final String URL = "URL";

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
        return view;

    }
    //    @Override
//    protected int getLayoutId() {
//        return R.layout.fm_video;
//    }

//    @Override
//    protected void initView() {
//
//        url = getArguments().getString(URL);
//        Glide.with(context)
//                .load(url)
//                .into(txvVideo.thumbImageView);
//        txvVideo.rl_touch_help.setVisibility(View.GONE);
//        txvVideo.setUp(url, url);
//
//    }

//    @Override
//    protected void loadData() {
//        txvVideo.startVideo();
//    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

//        if (txvVideo == null) {
//            return;
//        }
        if (isVisibleToUser) {
            //  txvVideo.goOnPlayOnResume();
        } else {
            //  txvVideo.goOnPlayOnPause();
        }

    }

    @Override
    public void onResume() {

        super.onResume();
//        if (txvVideo != null) {
//          //  txvVideo.goOnPlayOnResume();
//        }

    }

    @Override
    public void onPause() {
        super.onPause();
//        if (txvVideo != null) {
//          //  txvVideo.goOnPlayOnPause();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}


