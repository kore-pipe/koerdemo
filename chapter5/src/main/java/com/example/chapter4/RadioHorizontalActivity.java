package com.example.chapter4;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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