package com.example.duanshipindemo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends FragmentActivity {
    private List<String> urls = new ArrayList<>();
    private VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);
        verticalViewPager = findViewById(R.id.src_over);


        urls.add("http://50069.gzc.svp.tencent-cloud.com/gzc_1000035_0bc3o4aj6aaabaabsad2svqjk56dt53qbh2a.f0.mp4");
        urls.add("http://50069.gzc.svp.tencent-cloud.com/gzc_1000035_0b53quazwaabaiad35vjwjqjjbodtocqdg2a.f0.mp4");


        VerticalViewPagerAdapter verticalViewPagerAdapter = new VerticalViewPagerAdapter(MainActivity.this, getSupportFragmentManager());
        verticalViewPagerAdapter.setUrlList(urls);
        verticalViewPager.setAdapter(verticalViewPagerAdapter);
        verticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
