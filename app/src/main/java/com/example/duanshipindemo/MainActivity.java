package com.example.duanshipindemo;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends FragmentActivity {
    VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);
        verticalViewPager = findViewById(R.id.src_over);

        VerticalViewPagerAdapter verticalViewPagerAdapter = new VerticalViewPagerAdapter(getSupportFragmentManager());
        verticalViewPager.setAdapter(verticalViewPagerAdapter);
    }
}
