package com.example.chapter8;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.example.chapter8.adapter.ImagePagerAdapter;
import com.example.chapter8.adapter.MobilePagerAdapter;
import com.example.chapter8.bean.GoodsInfo;

import java.util.ArrayList;

public class FragmentDynamicActivity extends AppCompatActivity {

    private ArrayList<GoodsInfo> mGoodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dynamic);
        initPagerStrip();
        initViewPager();

    }
    //初始化翻页试图
    private void initViewPager() {
        ViewPager vp_content = findViewById(R.id.vp_content);

        mGoodsList = GoodsInfo.getDefaultList();

        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(this, mGoodsList);
        MobilePagerAdapter adapter = new MobilePagerAdapter(getSupportFragmentManager(),mGoodsList);

        vp_content.setAdapter(adapter);

        vp_content.setCurrentItem(0);

    }
    //初始化翻页标签栏
    private void initPagerStrip() {
        PagerTabStrip pts_tab  = findViewById(R.id.pts_tab);
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        pts_tab.setTextColor(Color.BLACK);

    }
}