package com.example.chapter8;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.chapter8.adapter.LaunchSimpleAdapter;

public class LaunchSimpleActivity extends AppCompatActivity {
    //声明引导页面的图片编辑数组
    private int[] lanuchImageArray = {
            R.drawable.guide_bg1,
            R.drawable.guide_bg2,
            R.drawable.guide_bg3,
            R.drawable.guide_bg4,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_simple);
        ViewPager vp_launch = findViewById(R.id.vp_launch);
        LaunchSimpleAdapter adapter = new LaunchSimpleAdapter(this,lanuchImageArray);
        vp_launch.setAdapter(adapter);

    }
}