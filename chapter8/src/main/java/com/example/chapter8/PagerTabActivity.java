package com.example.chapter8;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.example.chapter8.Util.ToastUtil;
import com.example.chapter8.adapter.ImagePagerAdapter;
import com.example.chapter8.bean.GoodsInfo;

import java.util.ArrayList;

public class PagerTabActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ArrayList<GoodsInfo> mGoodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_tab);
        initPagerStrip();
        initViewPager();
    }
     //初始化翻页试图
    private void initViewPager() {
        ViewPager vp_content = findViewById(R.id.vp_content);

        mGoodsList = GoodsInfo.getDefaultList();

        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(this, mGoodsList);
        vp_content.setAdapter(imagePagerAdapter);
        vp_content.addOnPageChangeListener(this);
        vp_content.setCurrentItem(0);

    }
    //初始化翻页标签栏
    private void initPagerStrip() {
        PagerTabStrip pts_tab  = findViewById(R.id.pts_tab);
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        pts_tab.setTextColor(Color.BLACK);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this, "您翻到的手机品牌是：" + mGoodsList.get(position).name);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}