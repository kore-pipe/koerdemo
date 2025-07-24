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

public class ButtonStyleActivity extends AppCompatActivity {
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_button_style);
           tv_result = findViewById(R.id.tv_result);



    }

    public void doClick(View view){
        String desc = String.format("%s 您点我了 %s",DateUtil.getNowTime(),((Button)view).getText());
        tv_result.setText(desc);



    }
}