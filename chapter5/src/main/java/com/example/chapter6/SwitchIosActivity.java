package com.example.chapter6;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SwitchIosActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView tv_result2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_ios);
       CheckBox ck_status =  findViewById(R.id.ck_status);
       tv_result2 = findViewById(R.id.tv_result2);
       ck_status.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String desc = String.format("你 %s 了这个switch",isChecked ? "打开" : "关闭");
        tv_result2.setText(desc);

    }
}