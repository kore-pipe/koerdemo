package com.example.koerdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.koerdemo.util.DateUtil;

public class ButtonEnableActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_enable;
    private Button btn_disable;
    private Button btn_text;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_enable);
        btn_enable = findViewById(R.id.btn_enable);
        btn_disable = findViewById(R.id.btn_disable);
        btn_text = findViewById(R.id.btn_text);
        tv = findViewById(R.id.tv_result);
        btn_disable.setOnClickListener(this);
        btn_enable.setOnClickListener(this);
        btn_text.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enable:
                btn_text.setEnabled(true);
                break;

            case R.id.btn_disable:
                btn_text.setEnabled(false);
                break;
            case R.id.btn_text:
                String desc = String.format("%s 您点我了", DateUtil.getNowTime());
                tv.setText(desc);
                break;

        }


    }
}