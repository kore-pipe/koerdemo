package com.example.chapter7_server;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RadioHorizontalActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_horizontal);
        RadioGroup rg_gender = findViewById(R.id.rg_gender);
        tv_result = findViewById(R.id.tv_result);
        rg_gender.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_male:
                tv_result.setText("你是一个帅气的男孩");
                break;
            case R.id.rb_fmale:
                tv_result.setText("你是一个漂亮的女孩");
                break;
        }
    }
}