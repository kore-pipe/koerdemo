package com.example.chapter8.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chapter8.R;
import com.example.chapter8.Util.ToastUtil;


public class LaunchFragment extends Fragment {




    public static LaunchFragment newInstance(int count,int position, int image_id) {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        args.putInt("count",count);
        args.putInt("position", position);
        args.putInt("image_id", image_id);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = getContext();
       Bundle args = getArguments();

        int position = args.getInt("position", 0);
        int imageId = args.getInt("image_id", 0);
        int count = args.getInt("count", 0);

        View view = LayoutInflater.from(context).inflate(R.layout.item_launch, container,false);
        ImageView iv_launch = view.findViewById(R.id.iv_launch);
        RadioGroup rg_indicate = view.findViewById(R.id.rg_indicate);
        Button btn_start = view.findViewById(R.id.btn_start);

        iv_launch.setImageResource(imageId);

        //每个页面都分配一组对应的单选按钮
        for (int j = 0; j < count; j++) {
            RadioButton radio = new RadioButton(context);
            radio.setLayoutParams(
                    new RadioGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
            );
            radio.setPadding(10,10,10,10);
            rg_indicate.addView(radio);
        }
        //当前位置的按钮高亮
        ((RadioButton)rg_indicate.getChildAt(position)).setChecked(true);
        //如果是最后一页，显示按钮进入主页
        if(position == count-1){
            btn_start.setVisibility(View.VISIBLE);
            btn_start.setOnClickListener(v -> ToastUtil.show(context,"欢迎开启美好生活"));
        }

        return view;
    }
}