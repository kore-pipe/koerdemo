package com.example.chapter7_server;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DrableShapeActivity extends AppCompatActivity implements View.OnClickListener {
    private View v_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drable_shape);
         v_content = findViewById(R.id.v_content);
        findViewById(R.id.btn_rect).setOnClickListener(this);
        findViewById(R.id.btn_oval).setOnClickListener(this);

        v_content.setBackgroundResource(R.drawable.shape_rect_gold);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_rect:
                v_content.setBackgroundResource(R.drawable.shape_rect_gold);
                break;
            case R.id.btn_oval:
                v_content.setBackgroundResource(R.drawable.shape_oval_rose);
               break;
        }

    }
}