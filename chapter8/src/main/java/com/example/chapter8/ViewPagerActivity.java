package com.example.chapter8;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.chapter8.Util.ToastUtil;
import com.example.chapter8.adapter.ImagePagerAdapter;
import com.example.chapter8.bean.GoodsInfo;

import java.util.ArrayList;


public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ArrayList<GoodsInfo> mGoodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);


        ViewPager vp_content = findViewById(R.id.vp_content);

        mGoodsList = GoodsInfo.getDefaultList();

        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(this, mGoodsList);
        vp_content.setAdapter(imagePagerAdapter);
        vp_content.addOnPageChangeListener(this);

    }

    //第一个参数表示当前页面的序号
//1. 第二个参数表示页面偏移的百分比，取值为0到1。
//2. 第三个参数表示页面的偏移距离。
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //翻页结束后出发，position表示当前滑倒了哪个页面
    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this, "您翻到的手机品牌是：" + mGoodsList.get(position).name);


    }

    //翻页状态改变时触发，0表示静止，1表示正在滑动，2表示滑动完毕
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}