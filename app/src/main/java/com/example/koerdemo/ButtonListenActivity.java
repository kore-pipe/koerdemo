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

public class ButtonListenActivity extends AppCompatActivity {
    private TextView tv_result_listen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_listen);
       Button tv_button = findViewById(R.id.btn_click_single);
        tv_result_listen = findViewById(R.id.btn_click_result);
       tv_button.setOnClickListener(new MyOnClickListener(tv_result_listen));




    }
    static class MyOnClickListener implements View.OnClickListener{
        private final TextView tv_result_listen;

        public MyOnClickListener(TextView tv_result_listen){
            this.tv_result_listen = tv_result_listen;

        }

        @Override
        public void onClick(View v) {
            String desc = String.format("%s 您点我了", DateUtil.getNowTime());
            tv_result_listen.setText(desc);


        }
    }

}
