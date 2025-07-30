package com.example.chapter7_client;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        findViewById(R.id.btn_finish).setOnClickListener(this);
        findViewById(R.id.tv_back).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_finish || v.getId() == R.id.tv_back){
            finish();
        }

    }
}