package com.example.chapter8.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chapter8.bean.GoodsInfo;
import com.example.chapter8.fragment.DynamicFragment;

import java.util.List;

public class MobilePagerAdapter extends FragmentPagerAdapter {

    private List<GoodsInfo> mGoodsInfos;

    public MobilePagerAdapter(FragmentManager fm, List<GoodsInfo> goodsInfos) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mGoodsInfos = goodsInfos;


    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DynamicFragment.newInstance(position,mGoodsInfos.get(position).pic
                ,mGoodsInfos.get(position).description);
    }

    @Override
    public int getCount() {
        return mGoodsInfos.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGoodsInfos.get(position).name;
    }
}
