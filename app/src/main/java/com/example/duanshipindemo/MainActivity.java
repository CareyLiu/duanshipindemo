package com.example.duanshipindemo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends FragmentActivity {
    VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);
        verticalViewPager = findViewById(R.id.src_over);

        VerticalViewPagerAdapter verticalViewPagerAdapter = new VerticalViewPagerAdapter(MainActivity.this, getSupportFragmentManager());
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
