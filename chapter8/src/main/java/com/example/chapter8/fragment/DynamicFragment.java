package com.example.chapter8.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter8.R;


public class DynamicFragment extends Fragment {




    public static DynamicFragment newInstance(int position ,int image_id, String desc) {
        DynamicFragment fragment = new DynamicFragment();
        //把参数打包，传入fragment
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        args.putString("desc", desc);
        fragment.setArguments(args);
        return fragment;
    }


   //创建碎片视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        Bundle args = getArguments();
        if(args != null){
            ImageView iv_pic = view.findViewById(R.id.iv_pic);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            iv_pic.setImageResource(args.getInt("image_id",R.drawable.huawei_p40));
            tv_desc.setText(args.getString("desc",""));
        }
        return view;
    }
}